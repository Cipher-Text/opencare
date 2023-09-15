const Pagination = ({ currentPage, totalPages, onPageChange, size, onSizeChange }) => {
  const handleSizeChange = (e) => {
    onSizeChange(e.target.value);
    onPageChange(0);
  }

  return (
    <nav aria-label="Page navigation" className="mt-3">
      <div className="flex justify-between">
        {totalPages > 0 ? 
          <ul className="inline-flex space-x-2">
            <li><button disabled={currentPage === 0}
              onClick={() => onPageChange(currentPage - 1)}
              className="flex items-center justify-center w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
              <svg className="w-4 h-4 fill-current" viewBox="0 0 20 20"><path d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clip-rule="evenodd" fill-rule="evenodd"></path></svg></button>
            </li>
            {
              totalPages < 7 ? (
                // all buttons
                [...Array(totalPages)].map((_, index) =>
                  <li><button onClick={() => onPageChange(index)}
                    className={currentPage == (index) ? "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline bg-indigo-300"
                      : "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100"}>
                    {index + 1}</button></li>
                )
              ) : ((totalPages > 6 && currentPage < 4) ? (
                // When Current Page is in first half
                (
                  <ul className="inline-flex space-x-2">
                    {[...Array(4)].map((_, index) =>
                      <li><button onClick={() => onPageChange(index)}
                        className={currentPage == (index) ? "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline bg-indigo-300"
                          : "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100"}>
                        {index + 1}</button>
                      </li>
                    )}
                    < li  className="text-indigo-600">......</li>
                    < li >
                      <button onClick={() => onPageChange(totalPages - 1)}
                        className="w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
                        {totalPages}</button>
                    </li>
                  </ul>
                )
              ) : (
                (totalPages > 6 && currentPage > (totalPages - 3)) ?
                  //  When Current Page is in last half
                  (
                    <ul className="inline-flex space-x-2">
                      <li>
                        <button onClick={() => onPageChange(0)}
                          className="w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
                          1</button>
                      </li>
                      <li className="text-indigo-600">......</li>
                      {[...Array(4)].map((_, index) =>
                        <li><button onClick={() => onPageChange(totalPages - 4 + index)}
                          className={currentPage == (totalPages - 4 + index) ? "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline bg-indigo-300"
                            : "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100"}>
                          {totalPages - 3 + index}</button>
                        </li>)
                      }
                    </ul>
                  ) : (
                    // When Current Page is in middle half
                    <ul className="inline-flex space-x-2">
                      <li>
                        <button onClick={() => onPageChange(0)}
                          className="w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
                          1</button>
                      </li>
                      <li className="text-indigo-600">......</li>
                      {[...Array(3)].map((_, index) =>
                        <li><button onClick={() => onPageChange(currentPage - 1 + index)}
                          className={(currentPage - 1 + index) == currentPage ? "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline bg-indigo-300"
                            : "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100"}>
                          {currentPage + index}</button>
                        </li>)
                      }
                      <li className="text-indigo-600">......</li>
                      < li >
                        <button onClick={() => onPageChange(totalPages - 1)}
                          className="w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
                          {totalPages}</button>
                      </li>
                    </ul>
                  )))}
            <li><button disabled={currentPage === totalPages - 1}
              onClick={() => onPageChange(currentPage + 1)}
              className="flex items-center justify-center w-10 h-10 text-indigo-600 transition-colors duration-150 bg-white rounded-full focus:shadow-outline hover:bg-indigo-100">
              <svg className="w-4 h-4 fill-current" viewBox="0 0 20 20"><path d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd" fill-rule="evenodd"></path></svg></button>
            </li>
          </ul>
        : <div></div>}
        <div>
        </div>
        <div>
          <div className="p-4">
            <select id="dropdown" className="block w-full px-4 py-2 border rounded-md 
            focus:ring focus:ring-blue-300 focus:border-blue-300 outline-none"  onChange={handleSizeChange} value={size}>
              <option value="5" className="text-gray-600">5</option>
              <option value="10" className="text-gray-600">10</option>
              <option value="50" className="text-gray-600">50</option>
              <option value="100" className="text-gray-600">100</option>
            </select>
          </div>
        </div>
      </div>

    </nav >
  );
}
export default Pagination;