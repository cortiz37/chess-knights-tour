import React, {Component} from 'react';
import {Card, Divider} from "antd";
import sample_board from "../../resources/sample_board.png";
import capture from "../../resources/capture.png";
import tour from "../../resources/tour.png";

class MainRoute extends Component {

    render() {
        return <div>
            <Card size="big" title="Chess - Knight's tour" style={{width: '100%'}}>
                <p>Rendering the chessboard and pieces with this component: <a target="_blank"
                                                                               href="https://chessboardjsx.com/">https://chessboardjsx.com/</a>
                </p>
                <p>
                    <img style={{maxWidth: 200}} src={sample_board}/>
                </p>
                <Divider/>
                <p>
                    Features
                    <ul>
                        <li>1: Minimum number of step to capture a piece (if possible)</li>
                        <li>2: Knight's tour</li>
                    </ul>
                    <div>
                        <img style={{maxWidth: 200, marginRight: 10}} src={capture}/>
                        <img style={{maxWidth: 200}} src={tour}/>
                    </div>
                </p>
            </Card>
        </div>
    }
}

export default MainRoute;