// @ts-ignore
/* eslint-disable */
import { request } from 'umi';
import { TableListItem } from './data';

/** Get list of institutions GET /api/institutions */
export async function institution(
  params: {
    // query
    /** Current page number */
    current?: number;
    /** Page capacity */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  return request<{
    data: TableListItem[];
    /** Total number of items in the list */
    total?: number;
    success?: boolean;
  }>('/api/institutions', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** New institution PUT /api/institutions */
export async function updateInstitution(data: { [key: string]: any }, options?: { [key: string]: any }) {
  return request<TableListItem>('/api/institutions', {
    data,
    method: 'PUT',
    ...(options || {}),
  });
}

/** New institution POST /api/institutions */
export async function addInstitution(data: { [key: string]: any }, options?: { [key: string]: any }) {
  return request<TableListItem>('/api/institutions', {
    data,
    method: 'POST',
    ...(options || {}),
  });
}

/** Delete institution DELETE /api/institutions */
export async function removeInstitution(data: { key: number[] }, options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/institutions', {
    data,
    method: 'DELETE',
    ...(options || {}),
  });
}
