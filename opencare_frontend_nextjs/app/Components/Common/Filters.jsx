import React, { useState } from "react";
import { Checkbox, Button, Typography, Menu, theme } from "antd";
import { CaretDownOutlined, CaretUpOutlined } from "@ant-design/icons";
import { blue, gray } from "@ant-design/colors";

const { Title, Text } = Typography;

const Filters = ({ title, items, selectedItems, value, handler }) => {
  const [showAll, setShowAll] = useState(false);
  const onChangeHandler = (e, item) => {
    console.log("called here", e.target.value, e.target.checked)
    let itemValue = '';
    switch (value){
      case "id":
        itemValue = item.id;
        break;
      case "name":
        itemValue = item.name;
        break;
    }

    if(e.target.checked){
      handler([...selectedItems, itemValue])
    }else{
      handler(selectedItems.filter((selectedItem) => selectedItem !== itemValue))
    }
  }

  // Define the maximum number of checkboxes to display initially
  const maxDisplayCount = 3;

  // Function to toggle the show all state
  const toggleShowAll = () => {
    setShowAll((prevShowAll) => !prevShowAll);
  };

  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const items2 = [title].map((x, index) => {
    return {
      key: `sub${index}`,
      label: <Title level={3}>{x}</Title>,
      children: items.map((_, j) => {
        return {
          key: _,
          label: `${_}`,
        };
      }),
    };
  });

  return (
    <div
      style={{
        margin: "0 16px",
      }}
    >
      <Menu
        theme="dark"
        mode="inline"
        defaultSelectedKeys={["1"]}
        defaultOpenKeys={["sub1"]}
      >
        {/* Parent menu item */}
        <Menu.SubMenu key="sub1" title={<span>{title}</span>}>
          {/* Render checkboxes */}
          {items
            .slice(0, showAll ? items.length : maxDisplayCount)
            .map((item, index) => (
              <Menu.Item key={index} style={{ backgroundColor: "transparent" }}>
                <Checkbox onChange={(e) => onChangeHandler(e, item)}
                          style={{ color: gray.at(1) }}
                          value={item.id}>{item.name}</Checkbox>
                {/*<Checkbox onChange={onChangeHandler} style={{ color: gray.at(1) }} value={item.name}>{item.name}</Checkbox>*/}
              </Menu.Item>
            ))}

          {/* Show more/less button */}
          {items.length > maxDisplayCount && (
            <Menu.Item
              key="showMoreLess"
              onClick={toggleShowAll}
              style={{ backgroundColor: "transparent" }}
            >
              {showAll ? (
                <Text style={{ color: blue.at(4) }}>
                  Show Less <CaretUpOutlined />
                </Text>
              ) : (
                <Text style={{ color: blue.at(4) }}>
                  Show More <CaretDownOutlined />
                </Text>
              )}
            </Menu.Item>
          )}
        </Menu.SubMenu>
      </Menu>
    </div>
  );
};

export default Filters;
