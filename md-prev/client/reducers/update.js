const initialState = {
    text: "initial",
    author: "Isaac Kita",
    count: 0
}

const update = (state=initialState, action)=> {
    switch(action.type){
        case 'UPDATE':
            return ({
                    text: action.text,
                    author: action.author,
                    count: action.count
                })
        default:
            return state;
    }
}

export default update
