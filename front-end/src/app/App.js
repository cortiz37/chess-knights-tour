import React, {Component} from 'react';
import '../css/App.css';
import 'antd/dist/antd.css'
import {Route, Switch, withRouter} from 'react-router-dom';
import {Layout, Spin} from 'antd';
import MainRoute from "./routes/MainRoute";
import ChessRoute from "./routes/ChessRoute";
import AppHeader from "./AppHeader";
import AppMenu from "./AppMenu";

const {Content} = Layout;

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        };
        window.app = this;
    }

    render() {
        if (this.state.isLoading) {
            return <div className="main-spin-container"><Spin size="large"/></div>
        }
        return (
            <Layout className="app-container">
                <AppHeader/>
                <Content className="app-content">
                    <div className="container container-main">
                        <AppMenu {...this.props} />
                        <Switch>
                            <Route exact path="/" render={(props) => <MainRoute {...this.state} />}/>
                            <Route path="/chess" render={(props) => <ChessRoute {...this.state} />}/>
                        </Switch>
                    </div>
                </Content>
            </Layout>
        );
    }
}

export default withRouter(App);
