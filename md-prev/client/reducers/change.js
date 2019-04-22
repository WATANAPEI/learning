const initialState = {
    text: "# This is h1 text.\n## This is h2 text."
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
