import { Breadcrumb} from 'antd';
const BreadCrumb = () => {
    const breadcrumbData = {
        home: ['Home'],
        hospitals: ['Home', 'Hospital'],
        doctors: ['Home', 'Doctors'],
        // Add more routes and corresponding breadcrumb data as needed
      };

    const currentRoute = window.location.pathname.substring(1); // Get the current route
    const breadcrumbItems = breadcrumbData[currentRoute] || []; 
    return (
        <Breadcrumb style={{
            margin: '16px 0',
          }}>
          {breadcrumbItems.map((item, index) => (
            <Breadcrumb.Item key={index}>{item}</Breadcrumb.Item>
          ))}
        </Breadcrumb>
      );
}
export default BreadCrumb;