# Contributing to Open-Care
We welcome contributions from everyone who shares our vision of improving healthcare accessibility. This guide explains how to contribute effectively.

## 📋 Table of Contents
- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Contribution Workflow](#contribution-workflow)
- [Development Practices](#development-practices)
- [Branch Management Strategy](#branch-management-strategy)
- [Commit Best Practices](#commit-best-practices)
- [Testing Guidelines](#testing-guidelines)
- [Pull Request Process](#pull-request-process)
- [Issue Reporting](#issue-reporting)
- [Community](#community)

## 🤝 Code of Conduct
By participating, you agree to abide by our [Code of Conduct](CODE_OF_CONDUCT.md). Key points:
- Be respectful and inclusive
- Assume positive intent
- Give constructive feedback
- Respect different perspectives
- Harassment or discrimination won't be tolerated

## 🚀 Getting Started
1. **Find an Issue**: Look for `good first issue` or `help wanted` labels
2. **Fork the Repository**
3. **Set Up Development Environment** (see repo-specific README)
4. **Join Discussions**: Share your approach before starting work

## 🔄 Contribution Workflow
1. **Sync with Main**: Regularly pull upstream changes
   ```bash
   git pull upstream main
   ```
2. **Create a Feature Branch**:
   ```bash
   git checkout -b feat/your-feature-name
   ```
3. **Commit Changes**:
   - Follow [Conventional Commits](https://www.conventionalcommits.org/)
   - Keep commits atomic
   - Write clear commit messages
4. **Test Thoroughly**: Ensure all tests pass
5. **Push Changes**:
   ```bash
   git push origin feat/your-feature-name
   ```

## 🛠️ Development Practices
### Code Quality
- Follow existing patterns and style
- Write clean, maintainable code
- Document complex logic
- Keep changes focused (one feature/fix per PR)

### Documentation
- Update relevant documentation
- Add comments for non-obvious code
- Keep commit messages and PR descriptions clear

## 🌳 Branch Management Strategy

### Branch Types
We use a structured branching approach to maintain code quality and streamline development:

#### Main Branches
- **`main`**: Production-ready code, always stable and deployable
- **`develop`**: Integration branch for features, contains the latest development changes

#### Supporting Branches
- **Feature Branches** (`feat/feature-name`): New features or enhancements
- **Bugfix Branches** (`fix/issue-description`): Bug fixes for the current release
- **Hotfix Branches** (`hotfix/critical-issue`): Critical fixes for production
- **Release Branches** (`release/v1.2.0`): Preparation for new releases

### Branch Naming Conventions
Use descriptive names following these patterns:
- `feat/user-authentication`
- `fix/login-validation-error`
- `hotfix/security-vulnerability`
- `release/v2.1.0`
- `docs/contributing-guidelines`
- `refactor/database-connection`

### Branch Lifecycle
1. **Create branch** from appropriate base (`main` for hotfixes, `develop` for features)
2. **Work on changes** with regular commits
3. **Keep branch updated** with base branch
4. **Create pull request** when ready
5. **Delete branch** after successful merge

### Best Practices
- Keep branches focused on single features or fixes
- Regularly sync with base branch to avoid conflicts
- Use descriptive branch names
- Delete stale branches promptly
- Avoid long-lived feature branches

## 💬 Commit Best Practices

### Commit Message Format
Follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

### Commit Types
- **`feat`**: New feature for the user
- **`fix`**: Bug fix for the user
- **`docs`**: Changes to documentation
- **`style`**: Code style changes (formatting, missing semicolons, etc.)
- **`refactor`**: Code change that neither fixes a bug nor adds a feature
- **`perf`**: Performance improvements
- **`test`**: Adding or updating tests
- **`build`**: Changes to build system or external dependencies
- **`ci`**: Changes to CI configuration files and scripts
- **`chore`**: Other changes that don't modify src or test files

### Examples of Good Commits

```bash
# Feature addition
feat(auth): add two-factor authentication support

Implement TOTP-based 2FA with QR code generation and backup codes.
Includes user preference settings and recovery mechanisms.

Closes #123

# Bug fix
fix(api): resolve null pointer exception in user validation

Add proper null checks before accessing user properties in the 
validation middleware to prevent server crashes.

Fixes #456

# Documentation update
docs(readme): update installation instructions for Docker setup

# Breaking change
feat(api)!: migrate to v2 authentication endpoints

BREAKING CHANGE: The /auth endpoint has been renamed to /api/v2/auth
and now requires additional headers for backwards compatibility.
```

### Commit Best Practices
- **Write clear, descriptive messages**: Explain what and why, not how
- **Use imperative mood**: "Add feature" not "Added feature"
- **Keep first line under 50 characters**: For better readability in logs
- **Use body for detailed explanation**: If the commit needs more context
- **Reference issues**: Use "Closes #123" or "Fixes #456"
- **Make atomic commits**: One logical change per commit
- **Test before committing**: Ensure code works and tests pass

### Multi-line Commit Template
Create a commit template file (`.gitmessage`) in your repository:

```
# <type>[optional scope]: <description>
# 
# [optional body]
# 
# [optional footer(s)]
# 
# Types: feat, fix, docs, style, refactor, perf, test, build, ci, chore
# Remember:
# - Use imperative mood in subject line
# - Do not end subject line with period
# - Separate subject from body with blank line
# - Use body to explain what and why vs. how
# - Reference issues and pull requests after body
```

Configure Git to use the template:
```bash
git config commit.template .gitmessage
```

## ✅ Testing Guidelines
- Write tests for new functionality
- Maintain test coverage
- Verify fixes with regression tests
- Test across supported platforms

## 🔍 Pull Request Process
1. **Open PR** from your fork to the main repository
2. **Use Template**: Fill out all sections of the PR template
3. **Review**: Address feedback through additional commits
4. **Merge**: Maintainers will merge when approved

### PR Requirements:
- Clear description of changes
- Reference related issues
- Passing CI tests
- Approval from at least 1 maintainer

## 🐛 Issue Reporting

### Before Reporting:
1. Search existing issues
2. Check documentation

### Good Issue Reports Include:
- Clear description of the problem
- Steps to reproduce
- Expected vs actual behavior
- Environment details
- Screenshots (if applicable)

## 🌍 Community

### Getting Help:
- Join our [community chat]()
- Ask in GitHub Discussions

### Recognition:
All contributors are:
- Listed in our contributors file
- Eligible for contributor swag
- Invited to community events

## 🎉 Thank You!
We appreciate your time and effort. Let's build better healthcare together!

For questions:
- Maintainers: maintainers@open-care.org
- Security: security@open-care.org