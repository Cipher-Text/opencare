"use client";
import {
  Layout,
  theme,
} from "antd";
import TopHeader from "./Components/Common/TopHeader";
const { Footer } = Layout;

export default function Home() {
  const {
    token: {colorBgContainer, borderRadiusLG},
  } = theme.useToken();
  return (
      <>
          <Layout
              style={{
                  background: colorBgContainer,
                  borderRadius: borderRadiusLG,
              }}
          >
              <div>Hello</div>
          </Layout>

          <Footer
              style={{
                  textAlign: "center",
              }}
          >
              Ant Design ©{new Date().getFullYear()} Created by Ant UED
          </Footer>
      </>

)
}
