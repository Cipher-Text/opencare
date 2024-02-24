import { Breadcrumb } from "antd";
import { useEffect, useState } from "react";

const BreadCrumb = () => {
  const breadcrumbData = {
    home: ["Home"],
    hospitals: ["Home", "Hospital"],
    doctors: ["Home", "Doctors"],
    // Add more routes and corresponding breadcrumb data as needed
  };

  const [currentRoute, setCurrentRoute] = useState("");

  useEffect(() => {
    // Access window object only in client-side
    setCurrentRoute(window.location.pathname.substring(1));
  }, []); // Empty dependency array ensures this effect runs only once after initial render

  const breadcrumbItems = breadcrumbData[currentRoute] || [];

  return (
    <Breadcrumb style={{ margin: "16px 0" }}>
      {breadcrumbItems.map((item, index) => (
        <Breadcrumb.Item key={index}>{item}</Breadcrumb.Item>
      ))}
    </Breadcrumb>
  );
};

export default BreadCrumb;
