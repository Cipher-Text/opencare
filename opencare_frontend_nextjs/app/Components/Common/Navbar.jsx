"use client"
import Link from 'next/link'
import Image from 'next/image'
import { usePathname } from 'next/navigation'

// export default function Navbar () {
//     const pathname = usePathname();

//     return (
//         <header className="w-full mt-5 text-gray-700 bg-white border-t border-gray-100 shadow-sm body-font">
//         <div className="container flex flex-col items-start justify-between p-6 mx-auto md:flex-row">
//             <a className="flex items-center mb-4 font-medium text-gray-900 title-font md:mb-0">
//             <Image
//             src="/logo.svg"
//             alt="Next.js Logo"
//             width={30}
//             height={0}
//             priority
//         />
//             <Image
//             src="/open_care.svg"
//             alt="Next.js Logo"
//             width={150}
//             height={0}
//             priority
//         />
//             </a>
//             <nav className="flex flex-wrap items-center justify-center pl-24 text-base md:ml-auto md:mr-auto">
//                 <Link href="/" className={`mr-5 font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/' ? 'bg-black text-white' : 'bg-red'}`}>Home</Link>
//                 <Link href="/hospitals" className={`mr-5 font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/hospitals' ? 'bg-black text-white' : ''}`}>Hospitals</Link>
//                 <Link href="/doctors" className={`mr-5 font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/doctors' ? 'bg-black text-white' : ''}`}>Doctors</Link>
//                 <Link href="/institutions" className={`font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/institutions' ? 'bg-black text-white' : ''}`}>Institutions</Link>
//             </nav>
//             <div className="items-center h-full">
//                 <a href="#_" className="mr-5 font-medium hover:text-gray-900">Login</a>
//                 <a href="#_"
//                     className="px-4 py-2 text-xs font-bold text-white uppercase transition-all duration-150 bg-teal-500 rounded shadow outline-none active:bg-teal-600 hover:shadow-md focus:outline-none ease">
//                     Sign Up
//                 </a>
//             </div>
//         </div>
//     </header>
//     );
// }

import React from 'react';
import { LaptopOutlined, NotificationOutlined, UserOutlined } from '@ant-design/icons';
import { Checkbox, Layout, Menu, theme } from 'antd';
import BreadCrumb from './BreadCrumb';
import Filters from './Filters';

const { Header, Content, Footer, Sider } = Layout;
const items = ['hospitals', 'doctors', 'institutes'].map((key) => ({
    key,
    menuName: `${key}`,
    menuLink: `${key}`,
}));
const items1 = ['1', '2', '3'].map((key) => ({
    key,
    label: `nav ${key}`,
}));
const items2 = [UserOutlined, LaptopOutlined, NotificationOutlined].map((icon, index) => {
    const key = String(index + 1);
    return {
        key: `sub${key}`,
        icon: React.createElement(icon),
        label: `subnav ${key}`,
        children: new Array(4).fill(null).map((_, j) => {
            const subKey = index * 4 + j + 1;
            return {
                key: subKey,
                label: `option${subKey}`,
            };
        }),
    };
});
const Checkboxitems = ['Checkbox 1', 'Checkbox 2', 'Checkbox 3', 'Checkbox 4', 'Checkbox 5'];
const DefaultContent = () => (
    <Content style={{ padding: '0 24px', minHeight: 280 }}>
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
                    display: 'flex',
                    alignItems: 'center',
                }}
            >
                <div className="demo-logo" />
                {/* <Menu
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={['2']}
          items={items1}
          style={{
            flex: 1,
            minWidth: 0,
          }}
        /> */}
                <Menu mode="horizontal" theme="dark">
                    {items.map(item => (
                        <Menu.Item key={item.menuName}>
                            <Link href={item.menuLink}>
                                {item.menuName}
                            </Link>
                        </Menu.Item>
                    ))}
                </Menu>
            </Header>
            <Content
                style={{
                    padding: '0 48px',
                }}
            >
                <BreadCrumb />
                <Layout
                    style={{
                        padding: '24px 0',
                        background: colorBgContainer,
                        borderRadius: borderRadiusLG,
                    }}
                >
                    <Sider
                        style={{
                            background: colorBgContainer,
                        }}
                        width={200}
                    >
                       <Filters style={{
                                height: '100%',
                            }} title='Title' items={Checkboxitems} />
                    </Sider>
                    {children ? children : <DefaultContent />}
                </Layout>
            </Content>
            <Footer
                style={{
                    textAlign: 'center',
                }}
            >
                Ant Design ©{new Date().getFullYear()} Created by Ant UED
            </Footer>
        </Layout>
    );
};
export default Navbar;