#!/usr/bin/env python3
"""
Automated CONTRIBUTORS.md Generator for Multiple Repositories
Combines contributor data from multiple GitHub repositories and generates a markdown file
"""

import requests
import json
import os
from datetime import datetime
from collections import defaultdict
import time

class ContributorAggregator:
    def __init__(self, github_token, repositories):
        self.github_token = github_token
        self.repositories = repositories
        self.headers = {
            'Authorization': f'token {github_token}',
            'Accept': 'application/vnd.github.v3+json'
        }
        self.contributors = defaultdict(lambda: {
            'name': '',
            'login': '',
            'avatar_url': '',
            'total_contributions': 0,
            'repos': []
        })

    def get_repo_contributors(self, repo):
        """Fetch contributors for a specific repository"""
        url = f"https://api.github.com/repos/{repo}/contributors"

        try:
            response = requests.get(url, headers=self.headers)
            response.raise_for_status()
            return response.json()
        except requests.exceptions.RequestException as e:
            print(f"Error fetching contributors for {repo}: {e}")
            return []

    def get_user_details(self, username):
        """Get user details including real name"""
        url = f"https://api.github.com/users/{username}"

        try:
            response = requests.get(url, headers=self.headers)
            response.raise_for_status()
            return response.json()
        except requests.exceptions.RequestException as e:
            print(f"Error fetching user details for {username}: {e}")
            return {}

    def aggregate_contributors(self):
        """Aggregate contributors from all repositories"""
        for repo in self.repositories:
            print(f"Fetching contributors for {repo}...")
            contributors = self.get_repo_contributors(repo)

            for contributor in contributors:
                login = contributor['login']
                contributions = contributor['contributions']

                # Get user details if we don't have them
                if not self.contributors[login]['name']:
                    user_details = self.get_user_details(login)
                    self.contributors[login]['name'] = user_details.get('name', login)
                    self.contributors[login]['login'] = login
                    self.contributors[login]['avatar_url'] = contributor['avatar_url']

                # Add contributions and repository
                self.contributors[login]['total_contributions'] += contributions
                self.contributors[login]['repos'].append({
                    'name': repo.split('/')[-1],
                    'contributions': contributions
                })

            # Rate limiting - be nice to GitHub API
            time.sleep(1)

    def categorize_contributors(self):
        """Categorize contributors by contribution level"""
        sorted_contributors = sorted(
            self.contributors.items(),
            key=lambda x: x[1]['total_contributions'],
            reverse=True
        )

        categories = {
            'core': [],      # 100+ contributions
            'active': [],    # 50-99 contributions
            'regular': [],   # 10-49 contributions
            'new': []        # 1-9 contributions
        }

        for login, data in sorted_contributors:
            total = data['total_contributions']
            if total >= 100:
                categories['core'].append((login, data))
            elif total >= 50:
                categories['active'].append((login, data))
            elif total >= 10:
                categories['regular'].append((login, data))
            else:
                categories['new'].append((login, data))

        return categories

    def generate_contributor_table(self, contributors, title, emoji):
        """Generate markdown table for a category of contributors"""
        if not contributors:
            return ""

        table = f"\n### {emoji} {title}\n"
        table += "| Avatar | Name | GitHub | Total Contributions | Repositories |\n"
        table += "|--------|------|--------|-------------------|--------------|\n"

        for login, data in contributors:
            name = data['name'] if data['name'] else login
            avatar = f'<img src="{data["avatar_url"]}" width="50" height="50">'
            github_link = f"[@{login}](https://github.com/{login})"
            total_contribs = data['total_contributions']
            repos = ', '.join([repo['name'] for repo in data['repos']])

            table += f"| {avatar} | **{name}** | {github_link} | {total_contribs} | {repos} |\n"

        return table

    def generate_repo_stats(self):
        """Generate repository statistics table"""
        repo_stats = {}

        for login, data in self.contributors.items():
            for repo_data in data['repos']:
                repo_name = repo_data['name']
                contributions = repo_data['contributions']

                if repo_name not in repo_stats:
                    repo_stats[repo_name] = {
                        'contributors': 0,
                        'total_commits': 0,
                        'top_contributor': {'login': '', 'contributions': 0}
                    }

                repo_stats[repo_name]['contributors'] += 1
                repo_stats[repo_name]['total_commits'] += contributions

                if contributions > repo_stats[repo_name]['top_contributor']['contributions']:
                    repo_stats[repo_name]['top_contributor'] = {
                        'login': login,
                        'contributions': contributions
                    }

        table = "\n## Repository Statistics\n\n"
        table += "| Repository | Contributors | Total Commits | Top Contributor |\n"
        table += "|------------|-------------|---------------|-----------------|\n"

        for repo_name, stats in repo_stats.items():
            top_login = stats['top_contributor']['login']
            top_contribs = stats['top_contributor']['contributions']

            table += f"| **{repo_name}** | {stats['contributors']} | {stats['total_commits']} | "
            table += f"[@{top_login}](https://github.com/{top_login}) ({top_contribs} commits) |\n"

        return table

    def generate_markdown(self):
        """Generate the complete CONTRIBUTORS.md file"""
        categories = self.categorize_contributors()

        # Header
        markdown = "# Contributors 🚀\n\n"
        markdown += "Thank you to all the amazing people who have contributed to our open source projects! "
        markdown += "This file is automatically generated and sorted by total contributions across all repositories.\n\n"
        markdown += "## Top Contributors\n"

        # Contributor tables
        markdown += self.generate_contributor_table(
            categories['core'],
            "Core Contributors (100+ contributions)",
            "🥇"
        )

        markdown += self.generate_contributor_table(
            categories['active'],
            "Active Contributors (50-99 contributions)",
            "🥈"
        )

        markdown += self.generate_contributor_table(
            categories['regular'],
            "Contributors (10-49 contributions)",
            "🥉"
        )

        markdown += self.generate_contributor_table(
            categories['new'],
            "New Contributors (1-9 contributions)",
            "🌟"
        )

        # Repository statistics
        markdown += self.generate_repo_stats()

        # Footer
        markdown += "\n## How to Contribute\n\n"
        markdown += "We welcome contributions from everyone! Here's how you can get involved:\n\n"
        markdown += "1. **Fork** the repository you want to contribute to\n"
        markdown += "2. **Clone** your fork locally\n"
        markdown += "3. **Create a branch** for your feature or bugfix\n"
        markdown += "4. **Make your changes** and test them\n"
        markdown += "5. **Submit a pull request** with a clear description\n\n"

        markdown += "## Recognition\n\n"
        markdown += "- 🎉 **First-time contributors** get a special welcome message\n"
        markdown += "- 🏆 **Monthly contributor awards** for outstanding contributions\n"
        markdown += "- 📋 **Contributor certificates** available upon request\n"
        markdown += "- 🎯 **Special mentions** in release notes\n\n"

        # Statistics
        total_contributors = len(self.contributors)
        total_commits = sum(data['total_contributions'] for data in self.contributors.values())

        markdown += "---\n\n"
        markdown += f"*This file is automatically updated daily. Last updated: {datetime.now().strftime('%Y-%m-%d')}*\n\n"
        markdown += f"*Total contributors across all repositories: {total_contributors}*\n"
        markdown += f"*Total commits across all repositories: {total_commits}*\n"

        return markdown

def main():
    # Configuration
    CONTRIBUTOR_TOKEN = os.getenv('CONTRIBUTOR_TOKEN')  # Set this as environment variable
    REPOSITORIES = [
        'Cipher-Text/opencare',
        'Cipher-Text/open-care-frontend',
        'Cipher-Text/open-care-backend',
        'Cipher-Text/open-care-mobile',
    ]

    if not CONTRIBUTOR_TOKEN:
        print("Error: CONTRIBUTOR_TOKEN environment variable not set")
        print("Please set your GitHub personal access token as an environment variable")
        return

    # Create aggregator and process
    aggregator = ContributorAggregator(CONTRIBUTOR_TOKEN, REPOSITORIES)

    print("Starting contributor aggregation...")
    aggregator.aggregate_contributors()

    print("Generating CONTRIBUTORS.md...")
    markdown_content = aggregator.generate_markdown()

    # Write to file
    with open('CONTRIBUTORS.md', 'w', encoding='utf-8') as f:
        f.write(markdown_content)

    print("✅ CONTRIBUTORS.md generated successfully!")
    print(f"Found {len(aggregator.contributors)} total contributors")

if __name__ == "__main__":
    main()