import{ Menu } from 'antd';
import {NavLink} from "react-router-dom";
import {
    // AppstoreAddOutlined,
    // BarsOutlined,
    DashboardOutlined,
    // ExportOutlined,
    // HomeOutlined,
    // ImportOutlined,
    // ProductOutlined,
    // ShoppingCartOutlined,
    // TagsOutlined,
    // TeamOutlined,
    // TrademarkCircleOutlined,
    // UserSwitchOutlined,
} from '@ant-design/icons';

const MenuList = ({darkTheme}) => {
    return (
        <Menu theme={darkTheme ? 'dark' : 'light'} mode="inline" className='menu-bar'>
            <Menu.Item key="personal_info" icon={<DashboardOutlined /> }>
                <NavLink to="/" className={({ isActive }) => isActive ? "active-link" : ""}>Personal Info.</NavLink>
            </Menu.Item>

            {/*<Menu.SubMenu key="category" icon={<TagsOutlined />} title="Category">*/}
            {/*    <Menu.Item key="add_new_category">*/}
            {/*        <NavLink to="/category/addNew" className={({ isActive }) => isActive ? "active-link" : ""}>All New</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="view_all_category">*/}
            {/*        <NavLink to="/category/viewAll" className={({ isActive }) => isActive ? "active-link" : ""}>View All</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}
            {/*<Menu.SubMenu key="brand" icon={<TrademarkCircleOutlined />} title="Brand">*/}
            {/*    <Menu.Item key="add_new_brand">*/}
            {/*        <NavLink to="/brand/addNew" className={({ isActive }) => isActive ? "active-link" : ""}>All New</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="view_all_brand">*/}
            {/*        <NavLink to="/brand/viewAll" className={({ isActive }) => isActive ? "active-link" : ""}>View All</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}
            {/*<Menu.SubMenu key="product" icon={<ProductOutlined />} title="Product">*/}
            {/*    <Menu.Item key="add_product">*/}
            {/*        <NavLink to="/product/addProduct" className={({ isActive }) => isActive ? "active-link" : ""}>Add Product</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="all_product">*/}
            {/*        <NavLink to="/product/allProduct" className={({ isActive }) => isActive ? "active-link" : ""}>All Product</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}
            {/*<Menu.SubMenu key="supplier" icon={<UserSwitchOutlined />} title="Supplier">*/}
            {/*    <Menu.Item key="add_supplier">*/}
            {/*        <NavLink to="/supplier/addSupplier" className={({ isActive }) => isActive ? "active-link" : ""}>Add Supplier</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="all_supplier">*/}
            {/*        <NavLink to="/supplier/allSupplier" className={({ isActive }) => isActive ? "active-link" : ""}>All Supplier</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="add_transaction">*/}
            {/*        <NavLink to="/supplier/addTransaction" className={({ isActive }) => isActive ? "active-link" : ""}>Add Transaction</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="all_transaction">*/}
            {/*        <NavLink to="/supplier/allTransaction" className={({ isActive }) => isActive ? "active-link" : ""}>All Transaction</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}
            {/*<Menu.SubMenu key="purchaseItem" icon={<ImportOutlined />} title="PurchaseItems">*/}
            {/*    <Menu.Item key="new_purchase">*/}
            {/*        <NavLink to="/purchaseitems/newPurchase" className={({ isActive }) => isActive ? "active-link" : ""}>New Purchase</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="all_purchase">*/}
            {/*        <NavLink to="/purchaseitems/allPurchase" className={({ isActive }) => isActive ? "active-link" : ""}>All Purchase</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}
            {/*<Menu.SubMenu key="customer" icon={<TeamOutlined />} title="Customer">*/}
            {/*    <Menu.Item key="add_type_customer">*/}
            {/*        <NavLink to="/customer/addType" className={({ isActive }) => isActive ? "active-link" : ""}>Add Type</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="add_new_customer">*/}
            {/*        <NavLink to="/customer/addNew" className={({ isActive }) => isActive ? "active-link" : ""}>Add New</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="view_all_customer">*/}
            {/*        <NavLink to="/customer/viewAll" className={({ isActive }) => isActive ? "active-link" : ""}>View All</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}

            {/*<Menu.SubMenu key="sale" icon={<ShoppingCartOutlined />} title="Sale">*/}
            {/*    <Menu.Item key="add_sale">*/}
            {/*        <NavLink to="/sale/addSale" className={({ isActive }) => isActive ? "active-link" : ""}>Add Sale</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="add_all_sale">*/}
            {/*        <NavLink to="/sale/allSale" className={({ isActive }) => isActive ? "active-link" : ""}>All Sale</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*    <Menu.Item key="sale_payment">*/}
            {/*        <NavLink to="/sale/payment" className={({ isActive }) => isActive ? "active-link" : ""}>Sale Payment</NavLink>*/}
            {/*    </Menu.Item>*/}
            {/*</Menu.SubMenu>*/}
        </Menu>
    );
};

export default MenuList;
