import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Layout} from 'antd';

const Header = Layout.Header;

class AppHeader extends Component {

    render() {
        return (
            <Header className="app-header">
                <div className="container">
                    <div className="app-title">
                        <Link to="/">Customer App</Link>
                    </div>
                </div>
            </Header>
        );
    }
}

export default withRouter(AppHeader);