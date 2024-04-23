"use client";
import Link from "next/link";
import { usePathname } from 'next/navigation'
import React from "react";
import { Layout, Menu } from "antd";

const { Header } = Layout;

const items =  ["", "hospitals", "doctors", "institutes"].map((_, index) => ({
    key: String(index + 1),
    label: (<Link href={_ === "" ? "/" : _}>{_ === "" ? "Home" : _ }</Link>),
}));
const TopHeader = () => {
    const pathname = usePathname();
    const indexList = ["/", "/hospitals", "/doctors", "/institutes"]
        .map((item, index) => item === pathname ? (index + 1).toString() : null)
        .filter((item) => item !== null);

    console.log(indexList)
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
            <Menu
                theme="dark"
                mode="horizontal"
                selectedKeys={indexList}
                items={items}
                style={{ flex: 1, minWidth: 0 }}
            />
        </Header>
    )
}
export default TopHeader;