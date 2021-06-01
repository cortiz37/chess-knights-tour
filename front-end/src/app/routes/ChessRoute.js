import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import Chess from "./views/Chess";

class ChessRoute extends Component {

    render() {
        return <Chess {...this.props}  />
    }
}

export default withRouter(ChessRoute);