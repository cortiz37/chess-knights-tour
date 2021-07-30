import React, {Component} from 'react';
import {Alert, Button, Card, List, notification, Tabs} from 'antd';
import Chessboard from "chessboardjsx";
import {CloseCircleOutlined, RightCircleOutlined} from "@ant-design/icons";
import {knightTour} from "../../../service/ChessService";

const {TabPane} = Tabs;

class Chess extends Component {

    constructor(props) {
        super(props);
        this.state = {
            width: 0,
            height: 0,
            mode: 'tour',
            blocked: false,
            allowMoves: true,
            position: {},
            size: 400
        };
        this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
    }

    updateMode(key) {
        this.setState({
            mode: key,
            position: {},
            allowMoves: true,
            blocked: false
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

    start() {
        if (this.state.mode === 'tour') {
            this.setState({
                blocked: true,
                allowMoves: false
            });
            knightTour(Object.keys(this.state.position)[0])
                .then(response => {
                    response.json().then(json => {
                        const moves = json.steps.map(step => {
                            const data = step.split(',');
                            return String.fromCharCode(parseInt(data[0]) + 97) + (parseInt(data[1]) + 1);
                        });
                        this.setState({
                            moves: moves,
                            currentMove: moves[0]
                        });
                    })
                }).catch(error => {
                    notification.open({
                        message: 'Error',
                        description: 'Something went wrong!',
                        icon: <CloseCircleOutlined/>,
                    });
                }
            );
        }
    }

    next() {
        const moves = this.state.moves;
        const nextIndex = moves.indexOf(this.state.currentMove) + 1;
        if (nextIndex < moves.length) {
            const nextMove = moves[nextIndex];
            const currentMove = this.state.currentMove;
            const position = this.state.position;

            position[currentMove] = 'bN';
            position[nextMove] = 'wN';

            const size = this.state.size > 401 ? 400 : this.state.size + 1;
            this.setState({
                position: position,
                currentMove: nextMove,
                size: size
            });
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
        return <div>
            <Chessboard
                onSquareClick={target => this.onClick(target)}
                onDrop={e => this.onDrop(e)}
                boardStyle={{float: 'left', marginRight: '20px'}}
                draggable={this.state.allowMoves}
                calcWidth={() => {
                    const defaultSize = this.state.size;
                    const min = this.state.width < this.state.height ? this.state.width : this.state.height;
                    return min < defaultSize ? min : defaultSize;
                }} position={this.state.position}/>
            <div style={{float: 'right'}}>
                <Card>
                    <div>
                        <Tabs defaultActiveKey="tour" onChange={value => this.updateMode(value)}>
                            <TabPane tab="Knight's tour" key="tour">
                                <Card>
                                    {
                                        this.isEmptyPosition() ?
                                            <Alert showIcon message="Click on the board to place the knight"
                                                   type="warning"/>
                                            :
                                            <div>
                                                {
                                                    this.state.blocked ?
                                                        <div>
                                                            <div>
                                                                <Button onClick={() => this.next()} type="primary"
                                                                        shape="round" icon={<RightCircleOutlined/>}>
                                                                    Next
                                                                </Button>
                                                            </div>
                                                            <div className="scrollable-container top-margin">
                                                                <List
                                                                    size="small"
                                                                    header={<div>Moves</div>}
                                                                    bordered
                                                                    dataSource={this.state.moves}
                                                                    renderItem={
                                                                        item =>
                                                                            <List.Item>{item === this.state.currentMove ? item + " (current)" : item}</List.Item>
                                                                    }
                                                                />
                                                            </div>
                                                        </div>
                                                        :
                                                        <div>
                                                            <Alert showIcon
                                                                   message="The knight can be moved to change the starting position"
                                                                   type="info"/>
                                                            <div className="top-margin"></div>
                                                            <Button onClick={() => this.start()} type="primary"
                                                                    shape="round" icon={<RightCircleOutlined/>}>
                                                                Start
                                                            </Button>
                                                        </div>
                                                }
                                            </div>
                                    }
                                </Card>
                            </TabPane>
                            <TabPane tab="Capture piece" key="capture">
                                Content of Tab Pane 2
                            </TabPane>
                        </Tabs>
                    </div>
                </Card>
            </div>
        </div>
    }
}

export default Chess;