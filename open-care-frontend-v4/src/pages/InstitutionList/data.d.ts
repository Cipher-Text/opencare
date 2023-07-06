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
