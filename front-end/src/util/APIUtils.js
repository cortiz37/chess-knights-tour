import {Modal} from "antd/lib/index";

export const request = (options, propagateError) => {
    const headers = new Headers({
        'Content-Type': 'application/json'
    });

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
        .then(response => {
            if (response.status === 200) {
                return response;
            }
            return response.json()
                .then(json => {
                    return json;
                })
        }).catch(error => {
            console.log(error)
            if (propagateError) {
                throw error;
            }
            return showError('Something went wrong.')
        });
};

export function showError(content, title = 'Error') {
    Modal.error({
        title: title,
        content: content
    });
}

export function showInfo(content, title = 'Info') {
    Modal.info({
        title: title,
        content: content
    });
}