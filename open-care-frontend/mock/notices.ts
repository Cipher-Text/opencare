import { Request, Response } from 'express';

const getNotices = (req: Request, res: Response) => {
  res.json({
    data: [
      {
        id: '000000001',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png',
        title: 'You received 14 new weekly newspapers',
        datetime: '2017-08-09',
        type: 'notification',
      },
      {
        id: '000000002',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png',
        title: 'Qu Nini you recommended has passed the third round of interview',
        datetime: '2017-08-08',
        type: 'notification',
      },
      {
        id: '000000003',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/kISTdvpyTAhtGxpovNWd.png',
        title: 'This template can distinguish between multiple notification types',
        datetime: '2017-08-07',
        read: true,
        type: 'notification',
      },
      {
        id: '000000004',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/GvqBnKhFgObvnSGkDsje.png',
        title: 'Icons on the left are used to distinguish between different types',
        datetime: '2017-08-07',
        type: 'notification',
      },
      {
        id: '000000005',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png',
        title: 'The content should not exceed two lines, and it will be automatically truncated when it exceeds',
        datetime: '2017-08-07',
        type: 'notification',
      },
      {
        id: '000000006',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg',
        title: 'Qu Lili commented on you',
        description: 'Description Description Description Description',
        datetime: '2017-08-07',
        type: 'message',
        clickClose: true,
      },
      {
        id: '000000007',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg',
        title: 'Zhu Pianyou replied to you',
        description: 'This template is used to remind who has interacted with you, and the avatar of "who" is placed on the left',
        datetime: '2017-08-07',
        type: 'message',
        clickClose: true,
      },
      {
        id: '000000008',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg',
        title: 'Title',
        description: 'This template is used to remind who has interacted with you, and the avatar of "who" is placed on the left',
        datetime: '2017-08-07',
        type: 'message',
        clickClose: true,
      },
      {
        id: '000000009',
        title: 'Mission name',
        description: '任务需要在 2017-01-12 20:00 前启动',
        extra: 'Has not started',
        status: 'todo',
        type: 'event',
      },
      {
        id: '000000010',
        title: 'Third Party Emergency Code Changes',
        description: 'Submitted by Guanlin on 2017-01-06, the code change task needs to be completed before 2017-01-07',
        extra: 'Due soon',
        status: 'urgent',
        type: 'event',
      },
      {
        id: '000000011',
        title: 'Information Security Exam',
        description: 'Assign Zhuer to complete the update and publish it by 2017-01-09',
        extra: '8 days have passed',
        status: 'doing',
        type: 'event',
      },
      {
        id: '000000012',
        title: 'ABCD version released',
        description: 'Submitted by Guanlin on 2017-01-06, the code change task needs to be completed before 2017-01-07',
        extra: 'In progress',
        status: 'processing',
        type: 'event',
      },
    ],
  });
};

export default {
  'GET /api/notices': getNotices,
};
