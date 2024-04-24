import Filters from "@/app/Components/Common/Filters";
import React from "react";
import { Layout, theme, } from "antd";

const { Content, Sider } = Layout;

const DefaultContent = () => (
    <Content style={{ padding: "0 24px", minHeight: 280 }}>
        Default Content
    </Content>
);

const Checkboxitems = [
    "Checkbox 1",
    "Checkbox 2",
    "Checkbox 3",
    "Checkbox 4",
    "Checkbox 5",
];
const SideBar = ({children}) => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return(
        <Layout
            style={{
                background: colorBgContainer,
                borderRadius: borderRadiusLG,
            }}
        >
            {/* <Sider
            style={{
              background: colorBgContainer,
            }}
            width={200}
          >
            <Filters
              style={{
                height: "100%",
              }}
              title="Title"
              items={Checkboxitems}
            />
          </Sider> */}
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
    );
}
export default SideBar;