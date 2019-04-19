import {connect} from 'react-redux'
import {changeText} from '../action'
import Preview from '../components/Preview'

const mapStateToProps = (state) => {
    return {
        text: state.text
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        textChanged: (text) => {
            dispatch(updateText(text))
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Preview)

