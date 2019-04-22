import {connect} from 'react-redux'
import Preview from '../components/Preview'

const mapStateToProps = (state) => {
    return {
        text: state.change.text,
    }
}

export default connect(mapStateToProps)(Preview)
