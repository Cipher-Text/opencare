import Link from "next/link";
import React from "react";
import { Layout, Menu, theme } from "antd";

const { Header, Content, Footer, Sider } = Layout;

const items = ["hospitals", "doctors", "institutes"].map((key) => ({
    key,
    menuName: `${key}`,
    menuLink: `${key}`,
}));
const TopHeader = () => {
    return(
        <Header
            style={{
                display: "flex",
                alignItems: "center",
                position: "sticky",
                top: 0,
                zIndex: 1,
            }}
        >
            <div className="demo-logo"/>
            <Menu mode="horizontal" theme="dark">
                {items.map((item) => (
                    <Menu.Item key={item.menuName}>
                        <Link href={item.menuLink}>{item.menuName}</Link>
                    </Menu.Item>
                ))}
            </Menu>
        </Header>
    )
}
export default TopHeader;