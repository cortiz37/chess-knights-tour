import React, {Component} from 'react';
import {Menu} from "antd";
import {HomeOutlined, TableOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";

class AppMenu extends Component {

    render() {
        return <div style={{marginBottom: '10px'}}>
            <Menu mode="horizontal" selectedKeys={[this.props.location.pathname]}>
                <Menu.Item key="/" icon={<HomeOutlined/>}>
                    <Link to="/">
                        Home
                    </Link>
                </Menu.Item>
                <Menu.Item key="/chess" icon={<TableOutlined/>}>
                    <Link to="/chess">
                        Chess
                    </Link>
                </Menu.Item>
            </Menu>
        </div>
    }
}

export default AppMenu;