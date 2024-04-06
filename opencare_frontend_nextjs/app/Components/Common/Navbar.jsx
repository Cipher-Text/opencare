"use client";
import Link from "next/link";

import React from "react";
import { Checkbox, Layout, Menu, theme } from "antd";
import Filters from "./Filters";

const { Header, Content, Footer, Sider } = Layout;
const items = ["hospitals", "doctors", "institutes"].map((key) => ({
  key,
  menuName: `${key}`,
  menuLink: `${key}`,
}));
const Checkboxitems = [
  "Checkbox 1",
  "Checkbox 2",
  "Checkbox 3",
  "Checkbox 4",
  "Checkbox 5",
];
const DefaultContent = () => (
  <Content style={{ padding: "0 24px", minHeight: 280 }}>
    Default Content
  </Content>
);
const Navbar = ({ children }) => {
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();
  return (
    <Layout>
      <Header
        style={{
          display: "flex",
          alignItems: "center",
          position: "sticky",
          top: 0,
          zIndex: 1,
        }}
      >
        <div className="demo-logo" />
        <Menu mode="horizontal" theme="dark">
          {items.map((item) => (
            <Menu.Item key={item.menuName}>
              <Link href={item.menuLink}>{item.menuName}</Link>
            </Menu.Item>
          ))}
        </Menu>
      </Header>
      <Layout
        style={{
          background: colorBgContainer,
          borderRadius: borderRadiusLG,
        }}
      >
        <Sider
          style={{
            overflow: "auto",
            height: "100vh",
            position: "fixed",
            left: 0,
            top: 60,
            bottom: 0,
          }}
        >
          <div className="demo-logo-vertical" />
          <Filters
            style={{
              height: "100%",
            }}
            title="Title"
            items={Checkboxitems}
          />
        </Sider>
        <Layout style={{ marginLeft: 200 }}>
          <Content
            style={{
              padding: "0 48px",
            }}
          >
            {children ? children : <DefaultContent />}
          </Content>
        </Layout>
      </Layout>

      <Footer
        style={{
          textAlign: "center",
        }}
      >
        Ant Design ©{new Date().getFullYear()} Created by Ant UED
      </Footer>
    </Layout>
  );
};
export default Navbar;
