//Dependencies
import React, { Component } from 'react';
import './Home.css';

//Actions
import { move, moveHead } from '../../actions/Controls.actions';
import { queryUltrasoundSensors } from '../../actions/Sensors.actions'

//Functions
import { handleKeyPress, handleKeyRelease } from './MovementControls';

class Home extends Component {

    constructor(props) {
        super(props);

        this.state = {
            moveStatus: "S",
            headServoStatus: "S",
            uSLowerMeasurement: 0.,
            uSFrontMeasurement: 0.,
            uSHeadMeasurement: 0.
        }
    }

    componentDidMount() {
        setInterval(() => { this.queryUltrasoundSensor() }, 500);
    }

    async move(direction) {
        let response = await move(direction);
    }

    async moveHead(direction){
        let response = await moveHead(direction);
    }

    async queryUltrasoundSensor() {
        let response = await queryUltrasoundSensors();
        if (response.data != null) {
            console.log(response.data)
            let uSLowerMeasurement = response.data.filter(a => a.name == "LOWER")[0].value;
            let uSFrontMeasurement = response.data.filter(a => a.name == "FRONT")[0].value;
            let uSHeadMeasurement = response.data.filter(a => a.name == "HEAD")[0].value;
            this.setState({ uSLowerMeasurement, uSFrontMeasurement, uSHeadMeasurement })
        }
    }

    handleKeyPress = (e) => {
        var newControlStatus = handleKeyPress(this.state.moveStatus, this.state.headServoStatus, e.key)
        if (newControlStatus && newControlStatus.newMoveStatus) {
            console.log("MoveStatus:" + newControlStatus.newMoveStatus)
            this.move(newControlStatus.newMoveStatus);
            this.setState({
                moveStatus: newControlStatus.newMoveStatus
            })
        }
        if (newControlStatus && newControlStatus.newhHeadServoStatus) {
            console.log("HeadServoStatus:" + newControlStatus.newhHeadServoStatus)
            this.moveHead(newControlStatus.newhHeadServoStatus);
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
            this.moveHead(newControlStatus.newhHeadServoStatus);
            this.setState({
                headServoStatus: newControlStatus.newhHeadServoStatus
            })
        }
    }

    render() {
        return (
            <div className="Home" onKeyDown={this.handleKeyPress} onKeyUp={this.handleKeyRelease} tabIndex="0">
                <p>FRONT: {this.state.uSFrontMeasurement}</p>
                <p>LOWER: {this.state.uSLowerMeasurement}</p>
                <p>HEAD: {this.state.uSHeadMeasurement}</p>
            </div>
        )
    }
} export default Home;