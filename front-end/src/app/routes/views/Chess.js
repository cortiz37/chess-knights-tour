import React, {Component} from 'react';
import Chessboard from "chessboardjsx";

class Chess extends Component {

    constructor(props) {
        super(props);
        this.state = {width: 0, height: 0};
        this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
    }

    componentDidMount() {
        this.updateWindowDimensions();
        window.addEventListener('resize', this.updateWindowDimensions);
    }

    componentWillUnmount() {
        window.removeEventListener('resize', this.updateWindowDimensions);
    }

    updateWindowDimensions() {
        this.setState({width: window.innerWidth, height: window.innerHeight});
    }

    render() {
        return <div>
            <Chessboard calcWidth={() => {
                const defaultSize = 400;
                const min = this.state.width < this.state.height ? this.state.width : this.state.height;
                return min < defaultSize ? min : defaultSize;
            }} position={{e3: "wP", f7: "bR"}}/>
        </div>
    }
}

export default Chess;