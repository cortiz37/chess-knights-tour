import React, {Component} from 'react';
import {Divider, Radio} from 'antd';
import Chessboard from "chessboardjsx";

class Chess extends Component {

    constructor(props) {
        super(props);
        this.state = {
            width: 0,
            height: 0,
            mode: 'tour',
            position: {}
        };
        this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
    }

    updateMode(e) {
        this.setState({
            mode: e.target.value,
            position: {}
        })
    }

    isEmptyPosition() {
        return Object.keys(this.state.position).length === 0;
    }

    onClick(target) {
        if (this.state.mode === 'tour' && this.isEmptyPosition()) {
            const position = {};
            position[target] = 'wN';
            this.setState({
                position: position
            })
        }
    }

    onDrop(e) {
        if (this.state.mode === 'tour') {
            const position = {};
            position[e.targetSquare] = 'wN';
            this.setState({
                position: position
            })
        }
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
        return <div className="miau">
            <Chessboard
                onSquareClick={target => this.onClick(target)}
                onDrop={e => this.onDrop(e)}
                boardStyle={{float: 'left', marginRight: '20px'}}
                calcWidth={() => {
                    const defaultSize = 400;
                    const min = this.state.width < this.state.height ? this.state.width : this.state.height;
                    return min < defaultSize ? min : defaultSize;
                }} position={this.state.position}/>
            <div>
                <Radio.Group defaultValue={this.state.mode} buttonStyle="solid"
                             onChange={value => this.updateMode(value)}>
                    <Radio.Button value="tour">Knight's tour</Radio.Button>
                    <Radio.Button value="capture">Capture piece</Radio.Button>
                </Radio.Group>
                {
                    this.state.mode === 'tour' && this.isEmptyPosition() ?
                        <p>Click on the board to place the knight</p>
                        :
                        <p>START...</p>
                }
            </div>
        </div>
    }
}

export default Chess;