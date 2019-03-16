const update = (state={}, action)=> {
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