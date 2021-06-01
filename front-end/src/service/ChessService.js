import {API_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function canCapture(pieceType, captureRequest) {
    return request({
        url: API_URL + '/capture',
        method: 'POST',
        body: JSON.stringify(captureRequest)
    });
}

export function knightTour(position, tourRequest) {
    return request({
        url: API_URL + '/tour',
        method: 'POST',
        body: JSON.stringify(tourRequest)
    });
}