import axios from 'axios';

import { host, port, datasource } from '../systemVariables/ServerInformation'

const resouce = "/sensors";

export async function queryUltrasoundSensors() {
    try {
        let response = await axios.get(
            host + port + datasource + resouce + '/ultrasound', {
            headers: {
                'Content-Type': 'application/json',
            }
        })
        return response;
    } catch(error){
        return error
    }
}