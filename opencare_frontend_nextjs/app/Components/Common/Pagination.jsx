const Pagination = ({ currentPage, totalPages, onPageChange, size, onSizeChange }) => {

  return (
    <nav aria-label="Page navigation" className="mt-3">
      <div className="flex justify-between">
        <div>
          <ul class="inline-flex space-x-2">
            <li><button disabled={currentPage === 0}
              onClick={() => onPageChange(currentPage - 1)}
              class="flex items-center justify-center w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
              <svg class="w-4 h-4 fill-current" viewBox="0 0 20 20"><path d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clip-rule="evenodd" fill-rule="evenodd"></path></svg></button>
            </li>
            {[...Array(currentPage < 3 ? currentPage : 3)].map((_, index) =>
              <li><button onClick={() => onPageChange(currentPage > 3 ? currentPage - 3 + index : index)}
                class="w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100">
                {currentPage > 3 ? currentPage - 2 + index : index + 1}</button></li>
            )}
            {[...Array(...Array(currentPage + 1 > totalPages ? 0 : (currentPage < 3 ? Math.min(7 - currentPage, totalPages - currentPage) :  Math.min(4, totalPages - currentPage))))].map((_, index) =>
              <li><button onClick={() => onPageChange(index + currentPage)}
                class={currentPage == (index + currentPage) ? "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline bg-indigo-300"
                  : "w-10 h-10 text-indigo-600 transition-colors duration-150 rounded-full focus:shadow-outline hover:bg-indigo-100"}>{index + currentPage + 1}</button></li>
            )}
            <li><button disabled={currentPage === totalPages - 1}
              onClick={() => onPageChange(currentPage + 1)}
              class="flex items-center justify-center w-10 h-10 text-indigo-600 transition-colors duration-150 bg-white rounded-full focus:shadow-outline hover:bg-indigo-100">
              <svg class="w-4 h-4 fill-current" viewBox="0 0 20 20"><path d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd" fill-rule="evenodd"></path></svg></button>
            </li>
          </ul>
        </div>
        <div>
          <div class="p-4">
            <select id="dropdown" class="block w-full px-4 py-2 border rounded-md 
            focus:ring focus:ring-blue-300 focus:border-blue-300 outline-none"  onChange={(event) => onSizeChange(event.target.value)} value={size}>
              <option value="5" className="text-gray-600">5</option>
              <option value="10" className="text-gray-600">10</option>
              <option value="50" className="text-gray-600">50</option>
              <option value="100" className="text-gray-600">100</option>
            </select>
          </div>
        </div>
      </div>

    </nav>
  );
}
export default Pagination;