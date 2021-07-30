import {API_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function canCapture(pieceType, captureRequest) {
    return request({
        url: API_URL + '/capture',
        method: 'POST',
        body: JSON.stringify(captureRequest)
    });
}

export function knightTour(position) {
    const tourRequest = {
        sourceColumn: position.charAt(0).charCodeAt(0) - 97,
        sourceRow: parseInt(position.charAt(1)) - 1,
        boardSize: 8
    };
    return request({
        url: API_URL + '/tour',
        method: 'POST',
        body: JSON.stringify(tourRequest)
    });
}