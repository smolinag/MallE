//Dependencies
import React, { Component } from 'react';
import './Home.css';

//Actions
import {move} from '../../actions/Controls.actions'

//Functions
import { handleKeyPress, handleKeyRelease } from './MovementControls';

class Home extends Component {

    constructor(props) {
        super(props);

        this.state = {
            moveStatus: "S",
            headServoStatus: "S"
        }
    }

    async move(direction){
        let response = await move(direction, 'MEDIUM');
    }

    handleKeyPress = (e) => {
        var newControlStatus = handleKeyPress(this.state.moveStatus, this.state.headServoStatus, e.key)
        if (newControlStatus && newControlStatus.newMoveStatus) {
            console.log("MoveStatus:" + newControlStatus.newMoveStatus)
            this.move(newControlStatus.newMoveStatus)
            this.setState({
                moveStatus: newControlStatus.newMoveStatus
            })
        }
        if (newControlStatus && newControlStatus.newhHeadServoStatus) {
            console.log("HeadServoStatus:" + newControlStatus.newhHeadServoStatus)
            this.setState({
                headServoStatus: newControlStatus.newhHeadServoStatus
            })
        }
    }

    handleKeyRelease = (e) => {
        var newControlStatus = handleKeyRelease(this.state.moveStatus, this.state.headServoStatus, e.key)
        if (newControlStatus && newControlStatus.newMoveStatus) {
            console.log("MoveStatus:" + newControlStatus.newMoveStatus)
            this.move(newControlStatus.newMoveStatus)
            this.setState({
                moveStatus: newControlStatus.newMoveStatus
            })
        }
        if (newControlStatus && newControlStatus.newhHeadServoStatus) {
            console.log("HeadServoStatus:" + newControlStatus.newhHeadServoStatus)
            this.setState({
                headServoStatus: newControlStatus.newhHeadServoStatus
            })
        }
    }

    render() {
        return (
            <div className="Home" onKeyDown={this.handleKeyPress} onKeyUp={this.handleKeyRelease} tabIndex="0">
            </div>
        )
    }
} export default Home;