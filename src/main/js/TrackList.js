const React = require("react");
const AudioTrack = require("./AudioTrack");

module.exports = class TrackList extends React.Component {

    render() {
        const tracks = this.props.audioTracks.map(track => {
            return <AudioTrack handleTrackSelected={this.props.handleTrackSelected} onPlayButton={this.props.onPlayButton} key={track.id} track={track}/>
        });
        return (

            <div className={"tracks"}>
                <div className={"top-bar display"}>
                    <div>Title</div>
                    <div>Album</div>
                    <div>Genres</div>
                    <div>Duration</div>
                </div>
                <div className={"track-list"}>
                    {tracks}
                </div>
            </div>

        )
    }

}