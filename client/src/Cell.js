import './styles.css'

export function Cell({x_index,y_index,value,callback}) {

    function handleClick(){
        callback(x_index,y_index)
    }

    return (
        <div className='cell' onClick={handleClick}>
            <p>{value === 1 ? "C" : value === 2 ? "P" : ""}</p>
        </div>
    )
}