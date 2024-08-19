import { DingdingOutlined } from '@ant-design/icons';
import { Button, Card, Steps, Result, Descriptions } from 'antd';
import { Fragment } from 'react';
import { GridContent } from '@ant-design/pro-layout';

import styles from './index.less';

const { Step } = Steps;

const desc1 = (
  <div className={styles.title}>
    <div style={{ margin: '8px 0 4px' }}>
      <span>Qu Lili</span>
      <DingdingOutlined style={{ marginLeft: 8, color: '#00A0E9' }} />
    </div>
    <div>2016-12-12 12:32</div>
  </div>
);

const desc2 = (
  <div style={{ fontSize: 12 }} className={styles.title}>
    <div style={{ margin: '8px 0 4px' }}>
      <span>Zhou Maomao</span>
      <a href="">
        <DingdingOutlined style={{ color: '#00A0E9', marginLeft: 8 }} />
        <span>Hurry up</span>
      </a>
    </div>
  </div>
);

const content = (
  <>
    <Descriptions title="Project name">
      <Descriptions.Item label="Project ID">23421</Descriptions.Item>
      <Descriptions.Item label="Principal">Qu Lili</Descriptions.Item>
      <Descriptions.Item label="Effective time">2016-12-12 ~ 2017-12-12</Descriptions.Item>
    </Descriptions>
    <br />
    <Steps progressDot current={1}>
      <Step title={<span style={{ fontSize: 14 }}>Create project</span>} description={desc1} />
      <Step
        title={<span style={{ fontSize: 14 }}>Department preliminary review</span>}
        description={desc2}
      />
      <Step title={<span style={{ fontSize: 14 }}>Financial review</span>} />
      <Step title={<span style={{ fontSize: 14 }}>Finish</span>} />
    </Steps>
  </>
);

const extra = (
  <Fragment>
    <Button type="primary">Back to list</Button>
    <Button>View project</Button>
    <Button>Print</Button>
  </Fragment>
);

export default () => (
  <GridContent>
    <Card bordered={false}>
      <Result
        status="success"
        title="Submitted successfully"
        subTitle="The submission result page is used to feedback the processing results of a series of operation tasks. If it is only a simple operation, use the Message global prompt feedback. This text area can display simple supplementary instructions. If there is a need to display 'receipts', the gray area below can display more complex content."
        extra={extra}
        style={{ marginBottom: 16 }}
      >
        {content}
      </Result>
    </Card>
  </GridContent>
);
