import { Card, message } from 'antd';
import ProForm, {
  ProFormDateRangePicker,
  ProFormDependency,
  ProFormDigit,
  ProFormRadio,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-form';
import { useRequest } from 'umi';
import type { FC } from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { fakeSubmitForm } from './service';
import styles from './style.less';

const BasicForm: FC<Record<string, any>> = () => {
  const { run } = useRequest(fakeSubmitForm, {
    manual: true,
    onSuccess: () => {
      message.success('Submitted successfully');
    },
  });

  const onFinish = async (values: Record<string, any>) => {
    run(values);
  };

  return (
    <PageContainer content="Form pages are used to collect or verify information from users, and basic forms are often used in form scenarios with fewer data items.">
      <Card bordered={false}>
        <ProForm
          hideRequiredMark
          style={{ margin: 'auto', marginTop: 8, maxWidth: 600 }}
          name="basic"
          layout="vertical"
          initialValues={{ public: '1' }}
          onFinish={onFinish}
        >
          <ProFormText
            width="md"
            label="Title"
            name="title"
            rules={[
              {
                required: true,
                message: 'Please enter a title',
              },
            ]}
            placeholder="Name the target"
          />
          <ProFormDateRangePicker
            label="Start date"
            width="md"
            name="date"
            rules={[
              {
                required: true,
                message: 'Please select a start and end date',
              },
            ]}
            placeholder={['Start date', 'End date']}
          />
          <ProFormTextArea
            label="Target description"
            width="xl"
            name="goal"
            rules={[
              {
                required: true,
                message: 'Please enter a goal description',
              },
            ]}
            placeholder="Please enter your staged work goals"
          />

          <ProFormTextArea
            label="Metrics"
            name="standard"
            width="xl"
            rules={[
              {
                required: true,
                message: 'Please enter a metric',
              },
            ]}
            placeholder="Please enter a metric"
          />

          <ProFormText
            width="md"
            label={
              <span>
                Client
                <em className={styles.optional}>（optional）</em>
              </span>
            }
            tooltip="Target audience"
            name="client"
            placeholder="Please describe the customers you serve, internal customers directly"
          />

          <ProFormText
            width="md"
            label={
              <span>
                Critic
                <em className={styles.optional}>（optional）</em>
              </span>
            }
            name="invites"
            placeholder="Please directly @name/job number, up to 5 people can be invited"
          />

          <ProFormDigit
            label={
              <span>
                Weights
                <em className={styles.optional}>（optional）</em>
              </span>
            }
            name="weight"
            placeholder="Please enter"
            min={0}
            max={100}
            width="xs"
            fieldProps={{
              formatter: (value) => `${value || 0}%`,
              parser: (value) => (value ? value.replace('%', '') : '0'),
            }}
          />

          <ProFormRadio.Group
            options={[
              {
                value: '1',
                label: 'Public',
              },
              {
                value: '2',
                label: 'Partially public',
              },
              {
                value: '3',
                label: 'Private',
              },
            ]}
            label="Open target"
            help="Customers and reviewers are shared by default"
            name="publicType"
          />
          <ProFormDependency name={['publicType']}>
            {({ publicType }) => {
              return (
                <ProFormSelect
                  width="md"
                  name="publicUsers"
                  fieldProps={{
                    style: {
                      margin: '8px 0',
                      display: publicType && publicType === '2' ? 'block' : 'none',
                    },
                  }}
                  options={[
                    {
                      value: '1',
                      label: 'Colleague A',
                    },
                    {
                      value: '2',
                      label: 'Colleague B',
                    },
                    {
                      value: '3',
                      label: 'Colleague C',
                    },
                  ]}
                />
              );
            }}
          </ProFormDependency>
        </ProForm>
      </Card>
    </PageContainer>
  );
};

export default BasicForm;
