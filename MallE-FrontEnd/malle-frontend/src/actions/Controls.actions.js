import axios from 'axios';

import { host, port, datasource } from '../systemVariables/ServerInformation'

const resouce = "/controls";

export async function move(direction) {
    try {
        let response = await axios.get(
            host + port + datasource + resouce + '/move', {
            params: {
                direction: direction,
            },
            headers: {
                'Content-Type': 'application/json',
            }
        })
        return response;
    } catch(error){
        return error
    }
}

export async function moveHead(direction) {
    try {
        let response = await axios.get(
            host + port + datasource + resouce + '/movehead', {
            params: {
                direction: direction,
            },
            headers: {
                'Content-Type': 'application/json',
            }
        })
        return response;
    } catch(error){
        return error
    }
}