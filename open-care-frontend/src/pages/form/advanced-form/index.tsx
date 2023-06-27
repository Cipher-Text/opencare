import { CloseCircleOutlined } from '@ant-design/icons';
import { Card, Col, Popover, Row, message } from 'antd';

import type { FC } from 'react';
import { useState } from 'react';
import ProForm, {
  ProFormDateRangePicker,
  ProFormSelect,
  ProFormText,
  ProFormTimePicker,
} from '@ant-design/pro-form';
import type { ProColumnType } from '@ant-design/pro-table';
import { EditableProTable } from '@ant-design/pro-table';
import { PageContainer, FooterToolbar } from '@ant-design/pro-layout';
import { fakeSubmitForm } from './service';
import styles from './style.less';

interface TableFormDateType {
  key: string;
  workId?: string;
  name?: string;
  department?: string;
  isNew?: boolean;
  editable?: boolean;
}
type InternalNamePath = (string | number)[];

const fieldLabels = {
  name: 'Warehouse name',
  url: 'Warehouse domain name',
  owner: 'Warehouse Administrator',
  approver: 'Approver',
  dateRange: 'Effective date',
  type: 'Warehouse type',
  name2: 'Task name',
  url2: 'Task Description',
  owner2: 'Executor',
  approver2: 'Responsible person',
  dateRange2: 'Effective date',
  type2: 'Task type',
};

const tableData = [
  {
    key: '1',
    workId: '00001',
    name: 'John Brown',
    department: 'New York No. 1 Lake Park',
  },
  {
    key: '2',
    workId: '00002',
    name: 'Jim Green',
    department: 'London No. 1 Lake Park',
  },
  {
    key: '3',
    workId: '00003',
    name: 'Joe Black',
    department: 'Sidney No. 1 Lake Park',
  },
];

interface ErrorField {
  name: InternalNamePath;
  errors: string[];
}

const AdvancedForm: FC<Record<string, any>> = () => {
  const [error, setError] = useState<ErrorField[]>([]);
  const getErrorInfo = (errors: ErrorField[]) => {
    const errorCount = errors.filter((item) => item.errors.length > 0).length;
    if (!errors || errorCount === 0) {
      return null;
    }
    const scrollToField = (fieldKey: string) => {
      const labelNode = document.querySelector(`label[for="${fieldKey}"]`);
      if (labelNode) {
        labelNode.scrollIntoView(true);
      }
    };
    const errorList = errors.map((err) => {
      if (!err || err.errors.length === 0) {
        return null;
      }
      const key = err.name[0] as string;
      return (
        <li key={key} className={styles.errorListItem} onClick={() => scrollToField(key)}>
          <CloseCircleOutlined className={styles.errorIcon} />
          <div className={styles.errorMessage}>{err.errors[0]}</div>
          <div className={styles.errorField}>{fieldLabels[key]}</div>
        </li>
      );
    });
    return (
      <span className={styles.errorIcon}>
        <Popover
          title="Form validation information"
          content={errorList}
          overlayClassName={styles.errorPopover}
          trigger="click"
          getPopupContainer={(trigger: HTMLElement) => {
            if (trigger && trigger.parentNode) {
              return trigger.parentNode as HTMLElement;
            }
            return trigger;
          }}
        >
          <CloseCircleOutlined />
        </Popover>
        {errorCount}
      </span>
    );
  };

  const onFinish = async (values: Record<string, any>) => {
    setError([]);
    try {
      await fakeSubmitForm(values);
      message.success('Submitted successfully');
    } catch {
      // console.log
    }
  };

  const onFinishFailed = (errorInfo: any) => {
    setError(errorInfo.errorFields);
  };

  const columns: ProColumnType<TableFormDateType>[] = [
    {
      title: 'Member name',
      dataIndex: 'name',
      key: 'name',
      width: '20%',
    },
    {
      title: 'Job number',
      dataIndex: 'workId',
      key: 'workId',
      width: '20%',
    },
    {
      title: 'Department',
      dataIndex: 'department',
      key: 'department',
      width: '40%',
    },
    {
      title: 'operate',
      key: 'action',
      valueType: 'option',
      render: (_, record: TableFormDateType, index, action) => {
        return [
          <a
            key="eidit"
            onClick={() => {
              action?.startEditable(record.key);
            }}
          >
            Edit
          </a>,
        ];
      },
    },
  ];

  return (
    <ProForm
      layout="vertical"
      hideRequiredMark
      submitter={{
        render: (props, dom) => {
          return (
            <FooterToolbar>
              {getErrorInfo(error)}
              {dom}
            </FooterToolbar>
          );
        },
      }}
      initialValues={{ members: tableData }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <PageContainer content="Advanced forms are often used in scenarios where a large amount of data is entered and submitted at one time.">
        <Card title="Warehouse management" className={styles.card} bordered={false}>
          <Row gutter={16}>
            <Col lg={6} md={12} sm={24}>
              <ProFormText
                label={fieldLabels.name}
                name="name"
                rules={[{ required: true, message: 'Please enter the warehouse name' }]}
                placeholder="Please enter the warehouse name"
              />
            </Col>
            <Col xl={{ span: 6, offset: 2 }} lg={{ span: 8 }} md={{ span: 12 }} sm={24}>
              <ProFormText
                label={fieldLabels.url}
                name="url"
                rules={[{ required: true, message: 'Please choose' }]}
                fieldProps={{
                  style: { width: '100%' },
                  addonBefore: 'http://',
                  addonAfter: '.com',
                }}
                placeholder="Please enter"
              />
            </Col>
            <Col xl={{ span: 8, offset: 2 }} lg={{ span: 10 }} md={{ span: 24 }} sm={24}>
              <ProFormSelect
                label={fieldLabels.owner}
                name="owner"
                rules={[{ required: true, message: 'Please select an administrator' }]}
                options={[
                  {
                    label: 'Fu Xiaoxiao',
                    value: 'xiao',
                  },
                  {
                    label: 'Zhou Maomao',
                    value: 'mao',
                  },
                ]}
                placeholder="Please select an administrator"
              />
            </Col>
          </Row>
          <Row gutter={16}>
            <Col lg={6} md={12} sm={24}>
              <ProFormSelect
                label={fieldLabels.approver}
                name="approver"
                rules={[{ required: true, message: 'Please select an approver' }]}
                options={[
                  {
                    label: 'Fu Xiaoxiao',
                    value: 'xiao',
                  },
                  {
                    label: 'Zhou Maomao',
                    value: 'mao',
                  },
                ]}
                placeholder="Please select an approver"
              />
            </Col>
            <Col xl={{ span: 6, offset: 2 }} lg={{ span: 8 }} md={{ span: 12 }} sm={24}>
              <ProFormDateRangePicker
                label={fieldLabels.dateRange}
                name="dateRange"
                fieldProps={{
                  style: {
                    width: '100%',
                  },
                }}
                rules={[{ required: true, message: 'Please select an effective date' }]}
              />
            </Col>
            <Col xl={{ span: 8, offset: 2 }} lg={{ span: 10 }} md={{ span: 24 }} sm={24}>
              <ProFormSelect
                label={fieldLabels.type}
                name="type"
                rules={[{ required: true, message: 'Please select warehouse type' }]}
                options={[
                  {
                    label: 'Private',
                    value: 'private',
                  },
                  {
                    label: 'Public',
                    value: 'public',
                  },
                ]}
                placeholder="Please select warehouse type"
              />
            </Col>
          </Row>
        </Card>
        <Card title="Task management" className={styles.card} bordered={false}>
          <Row gutter={16}>
            <Col lg={6} md={12} sm={24}>
              <ProFormText
                label={fieldLabels.name2}
                name="name2"
                rules={[{ required: true, message: 'Please enter' }]}
              />
            </Col>
            <Col xl={{ span: 6, offset: 2 }} lg={{ span: 8 }} md={{ span: 12 }} sm={24}>
              <ProFormText
                label={fieldLabels.url2}
                name="url2"
                rules={[{ required: true, message: 'Please choose' }]}
              />
            </Col>
            <Col xl={{ span: 8, offset: 2 }} lg={{ span: 10 }} md={{ span: 24 }} sm={24}>
              <ProFormSelect
                label={fieldLabels.owner2}
                name="owner2"
                rules={[{ required: true, message: 'Please select an administrator' }]}
                options={[
                  {
                    label: 'Fu Xiaoxiao',
                    value: 'xiao',
                  },
                  {
                    label: 'Zhou Maomao',
                    value: 'mao',
                  },
                ]}
              />
            </Col>
          </Row>
          <Row gutter={16}>
            <Col lg={6} md={12} sm={24}>
              <ProFormSelect
                label={fieldLabels.approver2}
                name="approver2"
                rules={[{ required: true, message: 'Please select an approver' }]}
                options={[
                  {
                    label: 'Fu Xiaoxiao',
                    value: 'xiao',
                  },
                  {
                    label: 'Zhou Maomao',
                    value: 'mao',
                  },
                ]}
                placeholder="Please select an approver"
              />
            </Col>
            <Col xl={{ span: 6, offset: 2 }} lg={{ span: 8 }} md={{ span: 12 }} sm={24}>
              <ProFormTimePicker
                label={fieldLabels.dateRange2}
                name="dateRange2"
                rules={[{ required: true, message: 'Please enter' }]}
                placeholder="Reminder time"
                fieldProps={{
                  style: {
                    width: '100%',
                  },
                }}
              />
            </Col>
            <Col xl={{ span: 8, offset: 2 }} lg={{ span: 10 }} md={{ span: 24 }} sm={24}>
              <ProFormSelect
                label={fieldLabels.type2}
                name="type2"
                rules={[{ required: true, message: 'Please select warehouse type' }]}
                options={[
                  {
                    label: 'Private',
                    value: 'private',
                  },
                  {
                    label: 'Public',
                    value: 'public',
                  },
                ]}
                placeholder="Please select warehouse type"
              />
            </Col>
          </Row>
        </Card>
        <Card title="Member management" bordered={false}>
          <ProForm.Item name="members">
            <EditableProTable<TableFormDateType>
              recordCreatorProps={{
                record: () => {
                  return {
                    key: `0${Date.now()}`,
                  };
                },
              }}
              columns={columns}
              rowKey="key"
            />
          </ProForm.Item>
        </Card>
      </PageContainer>
    </ProForm>
  );
};

export default AdvancedForm;
