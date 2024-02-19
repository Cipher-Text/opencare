
import { Typography, Badge, Flex, } from 'antd';

const { Title } = Typography;
const FilterBadge = () => {
    return (
        <div>
        <Flex wrap="wrap" gap="middle"
            style={{
                marginBottom: '16px',
            }}>
            <Flex wrap="wrap" gap="small">
                <Title level={5}>Filter 1:</Title>
                <Badge count={'checkbox 1'} color='#faad14' />
                <Badge count={'checkbox 2'} color='#faad14' />
                <Badge count={'checkbox 3'} color='#faad14' />
            </Flex>
            <Flex wrap="wrap" gap="small">
                <Title level={5}>Filter 1:</Title>
                <Badge count={'checkbox 1'} color='#faad14' />
                <Badge count={'checkbox 2'} color='#faad14' />
                <Badge count={'checkbox 3'} color='#faad14' />
            </Flex>
            <Flex wrap="wrap" gap="small">
                <Title level={5}>Filter 1:</Title>
                <Badge count={'checkbox 1'} color='#faad14' />
                <Badge count={'checkbox 2'} color='#faad14' />
                <Badge count={'checkbox 3'} color='#faad14' />
            </Flex>
        </Flex>
    </div>
      );
}
export default FilterBadge;