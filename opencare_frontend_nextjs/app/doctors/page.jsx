"use client";
import Navbar from "../Components/Common/Navbar";
import FilterBadge from "../Components/Common/FilterBadge";
import CommonList from "../Components/Common/List";
import { AudioOutlined } from "@ant-design/icons";
import { Input, Typography, Layout, Card, Space, Image, Flex } from "antd";
import { geekblue } from "@ant-design/colors";

const { Search } = Input;
const { Text, Title, Paragraph } = Typography;
const { Content } = Layout;

const onSearch = (value, _e, info) => console.log(info?.source, value);
export default function Doctors() {
  return (
      <div>This is Doctors page</div>
  );
}
