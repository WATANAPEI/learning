import initial from '../api/initial.txt'

const initialState = {
    text: initial
}

const change = (state=initialState, action) => {
    switch(action.type){
        case 'CHANGE':
            return ({
                text: action.text
            })
        default:
            return state;
    }
}

export default change
