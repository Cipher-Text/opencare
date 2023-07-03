import { DownOutlined, PlusOutlined } from '@ant-design/icons';
import {
  Avatar,
  Button,
  Card,
  Col,
  Dropdown,
  Input,
  List,
  Menu,
  Modal,
  Progress,
  Radio,
  Row,
} from 'antd';
import type { FC } from 'react';
import React, { useState } from 'react';

import { PageContainer } from '@ant-design/pro-layout';
import moment from 'moment';
import { useRequest } from 'umi';
import type { BasicListItemDataType } from './data';
import { addFakeList, queryFakeList, removeFakeList, updateFakeList } from './service';

const RadioButton = Radio.Button;
const RadioGroup = Radio.Group;
const { Search } = Input;

const Info: FC<{
  title: React.ReactNode;
  value: React.ReactNode;
  bordered?: boolean;
}> = ({ title, value, bordered }) => (
  <div className={styles.headerInfo}>
    <span>{title}</span>
    <p>{value}</p>
    {bordered && <em />}
  </div>
);

const ListContent = ({
  data: { owner, createdAt, percent, status },
}: {
  data: BasicListItemDataType;
}) => (
  <div className={styles.listContent}>
    <div className={styles.listContentItem}>
      <span>Owner</span>
      <p>{owner}</p>
    </div>
    <div className={styles.listContentItem}>
      <span>Starting time</span>
      <p>{moment(createdAt).format('YYYY-MM-DD HH:mm')}</p>
    </div>
    <div className={styles.listContentItem}>
      <Progress percent={percent} status={status} strokeWidth={6} style={{ width: 180 }} />
    </div>
  </div>
);

export const BasicList: FC = () => {
  const [done, setDone] = useState<boolean>(false);
  const [visible, setVisible] = useState<boolean>(false);
  const [current, setCurrent] = useState<Partial<BasicListItemDataType> | undefined>(undefined);

  const {
    data: listData,
    loading,
    mutate,
  } = useRequest(() => {
    return queryFakeList({
      count: 50,
    });
  });
  const { run: postRun } = useRequest(
    (method, params) => {
      if (method === 'remove') {
        return removeFakeList(params);
      }
      if (method === 'update') {
        return updateFakeList(params);
      }
      return addFakeList(params);
    },
    {
      manual: true,
      onSuccess: (result) => {
        mutate(result);
      },
    },
  );

  const list = listData?.list || [];

  const paginationProps = {
    showSizeChanger: true,
    showQuickJumper: true,
    pageSize: 5,
    total: list.length,
  };

  const showEditModal = (item: BasicListItemDataType) => {
    setVisible(true);
    setCurrent(item);
  };

  const deleteItem = (id: string) => {
    postRun('remove', { id });
  };

  const editAndDelete = (key: string | number, currentItem: BasicListItemDataType) => {
    if (key === 'edit') showEditModal(currentItem);
    else if (key === 'delete') {
      Modal.confirm({
        title: 'Delete task',
        content: 'Are you sure to delete this task?',
        okText: 'Confirm',
        cancelText: 'Cancel',
        onOk: () => deleteItem(currentItem.id),
      });
    }
  };

  const extraContent = (
    <div className={styles.extraContent}>
      <RadioGroup defaultValue="all">
        <RadioButton value="all">All</RadioButton>
        <RadioButton value="progress">In progress</RadioButton>
        <RadioButton value="waiting">Waiting</RadioButton>
      </RadioGroup>
      <Search
        className={styles.extraContentSearch}
        placeholder="Please enter"
        onSearch={() => ({})}
      />
    </div>
  );

  const MoreBtn: React.FC<{
    item: BasicListItemDataType;
  }> = ({ item }) => (
    <Dropdown
      overlay={
        <Menu onClick={({ key }) => editAndDelete(key, item)}>
          <Menu.Item key="edit">Edit</Menu.Item>
          <Menu.Item key="delete">Delete</Menu.Item>
        </Menu>
      }
    >
      <a>
        More <DownOutlined />
      </a>
    </Dropdown>
  );

  const handleDone = () => {
    setDone(false);
    setVisible(false);
    setCurrent({});
  };

  const handleSubmit = (values: BasicListItemDataType) => {
    setDone(true);
    const method = values?.id ? 'update' : 'add';
    postRun(method, values);
  };

  return (
    <div>
      <PageContainer>
        <div className={styles.standardList}>
          <Card bordered={false}>
            <Row>
              <Col sm={8} xs={24}>
                <Info title="My to do" value="8 missions" bordered />
              </Col>
              <Col sm={8} xs={24}>
                <Info
                  title="Average task processing time for this week"
                  value="32 minutes"
                  bordered
                />
              </Col>
              <Col sm={8} xs={24}>
                <Info title="Number of tasks completed this week" value="24 missions" />
              </Col>
            </Row>
          </Card>

          <Card
            className={styles.listCard}
            bordered={false}
            title="Basic list"
            style={{ marginTop: 24 }}
            bodyStyle={{ padding: '0 32px 40px 32px' }}
            extra={extraContent}
          >
            <List
              size="large"
              rowKey="id"
              loading={loading}
              pagination={paginationProps}
              dataSource={list}
              renderItem={(item) => (
                <List.Item
                  actions={[
                    <a
                      key="edit"
                      onClick={(e) => {
                        e.preventDefault();
                        showEditModal(item);
                      }}
                    >
                      Edit
                    </a>,
                    <MoreBtn key="more" item={item} />,
                  ]}
                >
                  <List.Item.Meta
                    avatar={<Avatar src={item.logo} shape="square" size="large" />}
                    title={<a href={item.href}>{item.title}</a>}
                    description={item.subDescription}
                  />
                  <ListContent data={item} />
                </List.Item>
              )}
            />
          </Card>
        </div>
      </PageContainer>
      <Button
        type="dashed"
        onClick={() => {
          setVisible(true);
        }}
        style={{ width: '100%', marginBottom: 8 }}
      >
        <PlusOutlined />
        Add
      </Button>
      <OperationModal
        done={done}
        visible={visible}
        current={current}
        onDone={handleDone}
        onSubmit={handleSubmit}
      />
    </div>
  );
};

export default BasicList;
