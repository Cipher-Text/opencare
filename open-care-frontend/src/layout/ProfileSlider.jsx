import { Avatar, Button } from 'antd';
import { LogoutOutlined, UserOutlined } from '@ant-design/icons';


const ProfileSlider = () => {
    return (
        <div style={{display: 'flex', alignItems: 'center'}}>
            <Avatar size="large" icon={<UserOutlined/>} style={{marginRight: '10px'}}/>
            <Button type="primary" icon={<LogoutOutlined/>} style={{marginRight: '10px'}}>Logout
            </Button>
        </div>
    );
}

export default ProfileSlider;