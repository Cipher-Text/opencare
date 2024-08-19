"use client";
import React, {useEffect, useState, useRef} from "react";
import {
    Input,
    Layout,
    Table,
    Button,
    Spin,
    Tag,
    theme,
    Modal,
    Image,
    Flex,
    Typography, Col, Row
} from "antd";
import BreadCrumb from "../Components/Common/BreadCrumb";
import Filters from "../Components/Common/Filters";

const {Search} = Input;
const { Title, Paragraph } = Typography;

const onSearch = (value, _e, info) => console.log(info?.source, value);
const {Content, Footer, Sider,} = Layout;

const columns = [
    {title: "Index", dataIndex: "id", width: 150},
    {
        title: "Name",
        dataIndex: "name",
        width: 350,
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
    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [size, setSize] = useState(5);
    const [selectedDistricts, setSelectedDistricts] = useState([]);
    const [selectedHospitalTypes, setSelectedHospitalTypes] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [districts, setDistricts] = useState([]);
    const [hospitalTypes, setHospitalTypes] = useState([]);
    const [organizationTypes, setOrganizationTypes] = useState([]);

    const [selectedRow, setSelectedRow] = useState(null);
    const [modalVisible, setModalVisible] = useState(false);

    const containerRef = useRef(null);
    const scrollThreshold = 50;

    const handleRowClick = (record) => {
        setSelectedRow(record);
        setModalVisible(true);
    };

    const closeModal = () => {
        setSelectedRow(null);
        setModalVisible(false);
    };

    const getHospitals = async () => {
        try {
            const queryParams = new URLSearchParams({
                districtIds: selectedDistricts,
                page: currentPage,
                size: size,
            });
            if(selectedHospitalTypes.length !== 0) queryParams.append('hospitalTypes', selectedHospitalTypes);
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
    }, [selectedDistricts, selectedHospitalTypes]);

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
        <>
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
                            selectedItems={selectedDistricts}
                            value="id"
                            handler={setSelectedDistricts}
                        />
                        <Filters
                            style={{
                                height: "100%",
                            }}
                            title="Hospital Types"
                            items={hospitalTypes}
                            selectedItems={selectedHospitalTypes}
                            value="name"
                            handler={setSelectedHospitalTypes}
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
                                {/*<FilterBadge/>*/}
                            </div>
                            <div>
                                <Table
                                    columns={columns}
                                    dataSource={data}
                                    pagination={false}
                                    loading={loading}
                                    rowKey={(record) => record.id}
                                    showHeader
                                    footer={footer}
                                    onRow={(record, rowIndex) => ({
                                        onClick: () => handleRowClick(record),
                                    })}
                                />
                            </div>
                        </Content>
                        <Modal
                            title=""
                            centered
                            visible={modalVisible}
                            onCancel={closeModal}
                            width={1000}
                        >
                            {selectedRow && (
                                <div>
                                    <Flex wrap="wrap" gap="large">
                                        <Image
                                            width={80}
                                            height={80}
                                            src="error"
                                            style={{
                                                borderRadius: "50%", // Make the image circular
                                                objectFit: "cover", // Ensure the image fills the circular shape
                                            }}
                                            fallback="data:image/png;base64,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"
                                        />
                                        <Typography
                                            style={{
                                                width: "70%",
                                            }}
                                        >
                                            <Title level={2}>{selectedRow.name}</Title>

                                            <Paragraph>
                                                Number of Beds: {selectedRow.numberOfBed}
                                            </Paragraph>
                                        </Typography>
                                    </Flex>
                                    <Title level={5}>Description</Title>
                                    <Paragraph>
                                        In the process of internal desktop applications development,
                                        many different design specs and implementations would be
                                        involved, which might cause designers and developers
                                        difficulties and duplication and reduce the efficiency of
                                        development.
                                    </Paragraph>
                                    <Row>
                                        <Col span={12}>
                                            <div>
                                                <Typography.Title level={5}>
                                                    Contact Info
                                                </Typography.Title>
                                                <p>
                                                    Contact Number: 01887520120
                                                </p>
                                                <p>
                                                    Email: test@gmail.com
                                                </p>
                                                <p>
                                                    Website: www.test.com
                                                </p>
                                            </div>
                                        </Col>
                                        <Col span={12}>
                                            <div>
                                                <Typography.Title level={5}>
                                                    Office Hours
                                                </Typography.Title>
                                                <p>Sunday : 8 AM - 9 PM</p>
                                                <p>Monday : 8 AM - 9 PM</p>
                                                <p>Tuesday : 8 AM - 9 PM</p>
                                                <p>Wednesday : 8 AM - 9 PM</p>
                                                <p>Thursday : 8 AM - 9 PM</p>
                                            </div>
                                        </Col>
                                    </Row>
                                </div>
                            )}
                        </Modal>
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
        </>);
}
