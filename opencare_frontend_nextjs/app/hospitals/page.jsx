"use client";
import React, {useEffect, useState, useRef} from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import {
    Avatar,
    Divider,
    List,
    Skeleton,
    Input,
    Layout,
    Badge,
    Table,
    Button,
    Spin,
    Tag,
    Menu,
    theme,
} from "antd";
import Navbar from "../Components/Common/Navbar";
import FilterBadge from "../Components/Common/FilterBadge";
import BreadCrumb from "../Components/Common/BreadCrumb";
import Link from "next/link";
import Filters from "../Components/Common/Filters";
import TopHeader from "../Components/Common/TopHeader";


const {Search} = Input;

const onSearch = (value, _e, info) => console.log(info?.source, value);
const {Header, Content, Footer, Sider,} = Layout;

const Checkboxitems = [
    "Checkbox 1",
    "Checkbox 2",
    "Checkbox 3",
    "Checkbox 4",
    "Checkbox 5",
];

const columns = [
    {title: "Index", dataIndex: "id", width: 150},
    {
        title: "Name",
        dataIndex: "name",
        width: 250,
    },
    {
        title: "Address",
        key: "address",
        render: (record) => (
            <span>
        {(record?.union ? record?.union?.bnName + ", " : "") +
            (record?.upazila ? record?.upazila?.bnName + ", " : "") +
            (record?.district ? record?.district?.bnName + ", " : "") +
            record?.district?.division?.bnName}
      </span>
        ),
    },
    {
        title: "Tags",
        key: "tags",
        render: (record) => (
            <span>
        {[record?.hospitalType, record?.organizationType].map((tag) => {
            let color = tag?.length > 7 ? "geekblue" : "green";
            return (
                <Tag color={color} key={tag}>
                    {tag}
                </Tag>
            );
        })}
      </span>
        ),
    },
    {
        title: "Number Of Bed",
        dataIndex: "numberOfBed",
        width: 150,
    },
];

export default function Hospitals() {
    const [loading, setLoading] = useState(false);
    const [hospitals, setHospitals] = useState([]);
    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [size, setSize] = useState(5);
    const [district, setDistrict] = useState("");
    const [hospitalType, setHospitalType] = useState("");
    const [totalPages, setTotalPages] = useState(0);
    const [height, setHeight] = useState(398);
    const [dec, setDec] = useState(true);
    const [districts, setDistricts] = useState([]);
    const [hospitalTypes, setHospitalTypes] = useState([]);
    const [organizationTypes, setOrganizationTypes] = useState([]);

    const containerRef = useRef(null);
    const scrollThreshold = 50;

    const getHospitals = async () => {
        try {
            const queryParams = new URLSearchParams({
                districtId: district,
                page: currentPage,
                size: size,
            });
            if(hospitalType !== "") queryParams.append('hospitalType', hospitalType);
            const res = await fetch(
                `http://localhost:6500/api/hospitals?${queryParams.toString()}`
            );
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        } catch (error) {
            console.error("Error fetching data:", error);
            return {hospitals: [], currentPage: 0, totalPages: 1};
        }
    };

    const handleScroll = (event) => {
        setCurrentPage(currentPage + 1);
    };
    const footer = () => (
        <div style={{textAlign: "center", marginTop: 16}}>
            {!loading && (
                <Button onClick={handleScroll} type="primary">
                    Load More
                </Button>
            )}
            {loading && <Spin style={{marginLeft: 8}}/>}
        </div>
    );
    //   const loadMoreData = () => {
    //     if (loading) {
    //       return;
    //     }
    //     setLoading(true);
    //     const queryParams = new URLSearchParams({
    //       districtId: district,
    //       page: currentPage,
    //       size: size,
    //     });
    //     fetch(`http://localhost:6500/api/hospitals?${queryParams.toString()}`)
    //       .then((res) => res.json())
    //       .then((body) => {
    //         setData([...data, ...body.hospitals]);
    //         setTotalPages(body.totalPages);
    //         setCurrentPage(body.currentPage);
    //         setLoading(false);
    //       })
    //       .catch(() => {
    //         setLoading(false);
    //       });
    //   };
    useEffect(() => {
        const loadMoreData = async () => {
            const newData = await getHospitals();
            setData([...data, ...newData.hospitals]);
            setTotalPages(newData.totalPages);
            setCurrentPage(newData.currentPage);
        };
        loadMoreData();
    }, [currentPage]);
    
    useEffect(() => {
        const loadMoreData = async () => {
            const newData = await getHospitals();
            setData([...newData.hospitals]);
            setTotalPages(newData.totalPages);
            setCurrentPage(newData.currentPage);
        };
        loadMoreData();
    }, [district, hospitalType]);

    //   const [hospitals, setHospitals] = useState([]);
    //   const [currentPage, setCurrentPage] = useState(0);
    //   const [size, setSize] = useState(5);
    //   const [totalPages, setTotalPages] = useState(0);
    //   const [districts, setDistricts] = useState([]);
    //   const [hospitalType, setHospitalType] = useState("");
    //   const [district, setDistrict] = useState("");

    //   const getHospitals = async () => {
    //     try {
    //       const queryParams = new URLSearchParams({
    //         districtId: district,
    //         page: currentPage,
    //         size: size,
    //       });

    //       const res = await fetch(
    //         `http://localhost:6500/api/hospitals?${queryParams.toString()}`
    //       );
    //       if (!res.ok) {
    //         throw new Error("Network response was not ok");
    //       }
    //       return res.json();
    //     } catch (error) {
    //       console.error("Error fetching data:", error);
    //       return { hospitals: [] };
    //     }
    //   };

    //   useEffect(() => {
    //     const fetchData = async () => {
    //       const data = await getHospitals();
    //       setHospitals(data.hospitals);
    //       setTotalPages(data.totalPages);
    //     };

    //     fetchData();
    //   }, [district, currentPage, size]);

    const getDistricts = async () => {
        try {
            const res = await fetch("http://localhost:6500/api/districts");
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        } catch (error) {
            console.error("Error fetching data:", error);
            return [];
        }
    };

    useEffect(() => {
        const fetchDistricts = async () => {
            const data = await getDistricts();
            setDistricts([...districts, ...data]);
        };

        fetchDistricts();
    }, []);

    const getHospitalTypes = async () => {
        try {
            const res = await fetch("http://localhost:6500/api/hospital-types");
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        } catch (error) {
            console.error("Error fetching data:", error);
            return [];
        }
    };

    useEffect(() => {
        const fetchHospitalTypes = async () => {
            const data = await getHospitalTypes();
            setHospitalTypes([...hospitalTypes, ...data]);
        };

        fetchHospitalTypes();
    }, []);
    const getOrganizationTypes = async () => {
        try {
            const res = await fetch("http://localhost:6500/api/organization-types");
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        } catch (error) {
            console.error("Error fetching data:", error);
            return [];
        }
    };

    useEffect(() => {
        const fetchOrganizationTypes = async () => {
            const data = await getOrganizationTypes();
            setOrganizationTypes([...organizationTypes, ...data]);
        };

        fetchOrganizationTypes();
    }, []);

    const {
        token: {colorBgContainer, borderRadiusLG},
    } = theme.useToken();

    return (
        <Layout>
            {console.log("dist", hospitalType, district)}
           <TopHeader/>
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
                    <div style={{
                        maxHeight: "95vh",
                        overflowY: "auto",
                    }}>
                        <div className="demo-logo-vertical"/>
                        <Filters
                            style={{
                                height: "100%",
                            }}
                            title="District"
                            items={districts}
                            handler={setDistrict}
                        />
                        <Filters
                            style={{
                                height: "100%",
                            }}
                            title="Hospital Types"
                            items={hospitalTypes}
                            handler={setHospitalType}
                        />
                        {/*<Filters*/}
                        {/*    style={{*/}
                        {/*        height: "100%",*/}
                        {/*    }}*/}
                        {/*    title="Organization Types"*/}
                        {/*    items={organizationTypes}*/}
                        {/*/>*/}
                    </div>
                </Sider>
                <Layout style={{marginLeft: 200}}>
                    <Content
                        style={{
                            padding: "0 48px",
                        }}
                    >
                        <Content
                            style={{
                                padding: "0 24px",
                                minHeight: 280,
                                backgroundColor: "#f8f4f4",
                            }}
                        >
                            <div
                                style={{
                                    position: "sticky",
                                    top: 67,
                                    zIndex: 10,
                                    backgroundColor: "#f8f4f4",
                                }}
                            >
                                <BreadCrumb/>
                                <Search
                                    placeholder="input search text"
                                    allowClear
                                    onSearch={onSearch}
                                    style={{
                                        width: "100%",
                                        marginBottom: "16px",
                                    }}
                                />

                                {/* Filter Badges */}
                                <FilterBadge/>
                            </div>

                            {/* <div
            id="scrollableDiv"
            style={{
              maxHeight: "400px",
              overflowY: "auto",
              border: "1px solid rgba(140, 140, 140, 0.35)",
            }}
            onScroll={handleScroll}
          >
            <Table columns={columns} dataSource={data} pagination={false} />
          </div> */}

                            <div>
                                <Table
                                    columns={columns}
                                    dataSource={data}
                                    pagination={false}
                                    loading={loading}
                                    rowKey={(record) => record.id}
                                    showHeader
                                    footer={footer}
                                />
                            </div>
                        </Content>
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
        </Layout>);
    // return (
    //   <Navbar>
    //     <Content
    //       style={{
    //         padding: "0 24px",
    //         minHeight: 280,
    //         backgroundColor: "#f8f4f4",
    //       }}
    //     >
    //       <div
    //         style={{
    //           position: "sticky",
    //           top: 67,
    //           zIndex: 10,
    //           backgroundColor: "#f8f4f4",
    //         }}
    //       >
    //         <BreadCrumb />
    //         <Search
    //           placeholder="input search text"
    //           allowClear
    //           onSearch={onSearch}
    //           style={{
    //             width: "100%",
    //             marginBottom: "16px",
    //           }}
    //         />
    //
    //         {/* Filter Badges */}
    //         <FilterBadge />
    //       </div>
    //
    //       {/* <div
    //         id="scrollableDiv"
    //         style={{
    //           maxHeight: "400px",
    //           overflowY: "auto",
    //           border: "1px solid rgba(140, 140, 140, 0.35)",
    //         }}
    //         onScroll={handleScroll}
    //       >
    //         <Table columns={columns} dataSource={data} pagination={false} />
    //       </div> */}
    //
    //       <div>
    //         <Table
    //           columns={columns}
    //           dataSource={data}
    //           pagination={false}
    //           loading={loading}
    //           rowKey={(record) => record.id}
    //           showHeader
    //           footer={footer}
    //         />
    //       </div>
    //     </Content>
    //   </Navbar>
    // );
}
