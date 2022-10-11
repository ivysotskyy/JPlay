const React = require("react");

module.exports = class AudioTrack extends React.Component {

    render() {
        const title = this.props.track.title
        return (
            <div className={"container"}>
                <button onClick={(event) => {
                    this.props.onPlayButton(this.props.track);
                }}>Play</button>
                <div className={"track display"}>
                    <h3>{title != null ? title : this.props.track.fileName}</h3>
                    <div>{this.props.track.author}</div>
                    <div>{this.props.track.album}</div>
                    <div>
                        {
                            this.props.track.genres.map(genre => {
                                return <a key={genre} href={"#"}>{genre + " " }</a>
                            })
                        }
                    </div>
                    <div>{this.formatTimeSeconds(this.props.track.durationSeconds)}</div>
                </div>
            </div>

        )
    }

    formatTimeSeconds = (seconds) => {
        let date = new Date(seconds * 1000);
        return date.toISOString().substring(11, 19);
    }

}