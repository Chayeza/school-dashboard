export interface IAddress {
  id?: number;
  studentId?: number;
  addressLineOne?: string;
  addressLineTwo?: string;
  city?: string;
  region?: string;
  postalCode?: number;
}

export const defaultValue: Readonly<IAddress> = {};
