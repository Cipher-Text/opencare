import { CloseCircleOutlined, RightOutlined } from '@ant-design/icons';
import { Button, Card, Result } from 'antd';
import { Fragment } from 'react';

import { GridContent } from '@ant-design/pro-layout';
import styles from './index.less';

const Content = (
  <Fragment>
    <div className={styles.title}>
      <span>Your submission has the following errors: </span>
    </div>
    <div style={{ marginBottom: 16 }}>
      <CloseCircleOutlined style={{ marginRight: 8 }} className={styles.error_icon} />
      <span>Your account has been frozen</span>
      <a style={{ marginLeft: 16 }}>
        <span>thaw immediately</span>
        <RightOutlined />
      </a>
    </div>
    <div>
      <CloseCircleOutlined style={{ marginRight: 8 }} className={styles.error_icon} />
      <span>Your account is not yet eligible</span>
      <a style={{ marginLeft: 16 }}>
        <span>Upgrade immediately</span>
        <RightOutlined />
      </a>
    </div>
  </Fragment>
);

export default () => (
  <GridContent>
    <Card bordered={false}>
      <Result
        status="error"
        title="Submission Failed"
        subTitle="Please check and modify the following information before resubmitting."
        extra={
          <Button type="primary">
            <span>Back to modify</span>
          </Button>
        }
        style={{ marginTop: 48, marginBottom: 16 }}
      >
        {Content}
      </Result>
    </Card>
  </GridContent>
);
