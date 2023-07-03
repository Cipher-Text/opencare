export type GithubIssueItem = {
  url: string;
  id: number;
  number: number;
  title: string;
  labels: {
    name: string;
    color: string;
  }[];
  state: string;
  comments: number;
  created_at: string;
  updated_at: string;
  closed_at?: string;
};

type Division = {
  id: number;
  name: string;
  bnName: string;
  url: string;
};

type District = {
  id: number;
  division: Division;
  name: string;
  bnName: string;
  lat: string;
  lon: string;
  url: string;
};

export type InstituteItem = {
  url: string;
  id: number;
  acronym: string;
  name: string;
  bnName: string;
  establishedYear: number;
  enroll: number;
  district: District;
  hospitalType: string;
  organizationType: string;
  lat: string;
  lon: string;
};