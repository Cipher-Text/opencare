import type { FC } from 'react';
import {
  ModalForm,
  ProFormSelect,
  ProFormDateTimePicker,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-form';
import type { BasicListItemDataType } from '../data.d';
import styles from '../style.less';
import { Button, Result } from 'antd';

type OperationModalProps = {
  done: boolean;
  visible: boolean;
  current: Partial<BasicListItemDataType> | undefined;
  onDone: () => void;
  onSubmit: (values: BasicListItemDataType) => void;
};

const OperationModal: FC<OperationModalProps> = (props) => {
  const { done, visible, current, onDone, onSubmit, children } = props;
  if (!visible) {
    return null;
  }
  return (
    <ModalForm<BasicListItemDataType>
      visible={visible}
      title={done ? null : `Task${current ? 'Edit' : 'Add'}`}
      className={styles.standardListForm}
      width={640}
      onFinish={async (values) => {
        onSubmit(values);
      }}
      initialValues={current}
      submitter={{
        render: (_, dom) => (done ? null : dom),
      }}
      trigger={<>{children}</>}
      modalProps={{
        onCancel: () => onDone(),
        destroyOnClose: true,
        bodyStyle: done ? { padding: '72px 0' } : {},
      }}
    >
      {!done ? (
        <>
          <ProFormText
            name="title"
            label="Mission Name"
            rules={[{ required: true, message: 'Please enter a task name' }]}
            placeholder="Please enter"
          />
          <ProFormDateTimePicker
            name="createdAt"
            label="Starting time"
            rules={[{ required: true, message: 'Please select a start time' }]}
            fieldProps={{
              style: {
                width: '100%',
              },
            }}
            placeholder="Please choose"
          />
          <ProFormSelect
            name="owner"
            label="Task leader"
            rules={[{ required: true, message: 'Please select a task leader' }]}
            options={[
              {
                label: 'Fu Xiaoxiao',
                value: 'xiao',
              },
              {
                label: '周毛毛',
                value: 'mao',
              },
            ]}
            placeholder="Zhou Maomao"
          />
          <ProFormTextArea
            name="subDescription"
            label="Product Description"
            rules={[{ message: 'Please enter a product description of at least five characters!', min: 5 }]}
            placeholder="Please enter at least five characters"
          />
        </>
      ) : (
        <Result
          status="success"
          title="Successful operation"
          subTitle="A series of information descriptions, very short and can also be punctuated."
          extra={
            <Button type="primary" onClick={onDone}>
              Knew
            </Button>
          }
          className={styles.formResult}
        />
      )}
    </ModalForm>
  );
};

export default OperationModal;
