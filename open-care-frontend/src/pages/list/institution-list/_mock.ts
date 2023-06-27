// eslint-disable-next-line import/no-extraneous-dependencies
import type { Request, Response } from 'express';
import { parse } from 'url';
import type { TableListItem, TableListParams } from './data';

// mock tableListDataSource
const genList = (current: number, pageSize: number) => {
  const tableListDataSource: TableListItem[] = [];

  for (let i = 0; i < pageSize; i += 1) {
    const index = (current - 1) * 10 + i;
    tableListDataSource.push({
      id: index,
      acronym: 'DMC',
      name: 'Dhaka Medical College',
      bnName: 'ঢাকা মেডিকেল কলেজ',
      establishedYear: 1990,
      enroll: 200,
      districtName: 'Dhaka',
      hospitalType: 'Government',
      organizationType: 'College',
      lat: '4646',
      lon: '3888',
      url: 'http://www.dmc.gov.bd',
    });
  }
  tableListDataSource.reverse();
  return tableListDataSource;
};

let tableListDataSource = genList(1, 100);

function getInstitution(req: Request, res: Response, u: string) {
  let realUrl = u;
  if (!realUrl || Object.prototype.toString.call(realUrl) !== '[object String]') {
    realUrl = req.url;
  }
  const { current = 1, pageSize = 10 } = req.query;
  const params = parse(realUrl, true).query as unknown as TableListParams;

  let dataSource = [...tableListDataSource].slice(
    ((current as number) - 1) * (pageSize as number),
    (current as number) * (pageSize as number),
  );
  if (params.sorter) {
    const sorter = JSON.parse(params.sorter as any);
    dataSource = dataSource.sort((prev, next) => {
      let sortNumber = 0;
      Object.keys(sorter).forEach((key) => {
        if (sorter[key] === 'descend') {
          if (prev[key] - next[key] > 0) {
            sortNumber += -1;
          } else {
            sortNumber += 1;
          }
          return;
        }
        if (prev[key] - next[key] > 0) {
          sortNumber += 1;
        } else {
          sortNumber += -1;
        }
      });
      return sortNumber;
    });
  }
  if (params.filter) {
    const filter = JSON.parse(params.filter as any) as Record<string, string[]>;
    if (Object.keys(filter).length > 0) {
      dataSource = dataSource.filter((item) => {
        return Object.keys(filter).some((key) => {
          if (!filter[key]) {
            return true;
          }
          if (filter[key].includes(`${item[key]}`)) {
            return true;
          }
          return false;
        });
      });
    }
  }

  if (params.name) {
    dataSource = dataSource.filter((data) => data.name.includes(params.name || ''));
  }

  let finalPageSize = 10;
  if (params.pageSize) {
    finalPageSize = parseInt(`${params.pageSize}`, 10);
  }

  const result = {
    data: dataSource,
    total: tableListDataSource.length,
    success: true,
    pageSize: finalPageSize,
    current: parseInt(`${params.currentPage}`, 10) || 1,
  };

  return res.json(result);
}

function postInstitution(req: Request, res: Response, u: string, b: Request) {
  let realUrl = u;
  if (!realUrl || Object.prototype.toString.call(realUrl) !== '[object String]') {
    realUrl = req.url;
  }

  const body = (b && b.body) || req.body;
  const { name, desc, key } = body;

  switch (req.method) {
    /* eslint no-case-declarations:0 */
    case 'DELETE':
      tableListDataSource = tableListDataSource.filter((item) => key.indexOf(item.id) === -1);
      break;
    case 'POST':
      (() => {
        const i = Math.ceil(Math.random() * 10000);
        const newInstitution = {
          id: tableListDataSource.length,
          acronym: 'DMC',
          name: 'Dhaka Medical College',
          bnName: 'ঢাকা মেডিকেল কলেজ',
          establishedYear: 1990,
          enroll: 200,
          districtName: 'Dhaka',
          hospitalType: 'Government',
          organizationType: 'College',
          lat: '4646',
          lon: '3888',
          url: 'http://www.dmc.gov.bd',
        };
        tableListDataSource.unshift(newInstitution);
        return res.json(newInstitution);
      })();
      return;

    case 'PUT':
      (() => {
        let newInstitution = {};
        tableListDataSource = tableListDataSource.map((item) => {
          if (item.id === key) {
            newInstitution = { ...item, desc, name };
            return { ...item, desc, name };
          }
          return item;
        });
        return res.json(newInstitution);
      })();
      return;
    default:
      break;
  }

  const result = {
    list: tableListDataSource,
    pagination: {
      total: tableListDataSource.length,
    },
  };

  res.json(result);
}

export default {
  'GET /api/institutions': getInstitution,
  'POST /api/institutions': postInstitution,
  'DELETE /api/institutions': postInstitution,
  'PUT /api/institutions': postInstitution,
};
