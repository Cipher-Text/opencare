const SearchForm = () => {
    return (

        <form>
            <div class="mb-6">
                <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">District</label>
                <select id="dropdown" class="block w-full px-4 py-2 border rounded-md focus:ring focus:ring-blue-300 
                focus:border-blue-300 outline-none" required>
                    <option value="5" className="text-gray-600">5</option>
                    <option value="10" className="text-gray-600">10</option>
                    <option value="50" className="text-gray-600">50</option>
                    <option value="100" className="text-gray-600">100</option>
                </select>
            </div>
            <div class="mb-6">
                <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Hospital Type</label>
                <select id="dropdown" class="block w-full px-4 py-2 border rounded-md focus:ring focus:ring-blue-300 
                focus:border-blue-300 outline-none" required>
                    <option value="5" className="text-gray-600">5</option>
                    <option value="10" className="text-gray-600">10</option>
                    <option value="50" className="text-gray-600">50</option>
                    <option value="100" className="text-gray-600">100</option>
                </select>
            </div>
            <div class="mb-6">
                <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Organization Type</label>
                <select id="dropdown" class="block w-full px-4 py-2 border rounded-md focus:ring focus:ring-blue-300 
                focus:border-blue-300 outline-none" required>
                    <option value="5" className="text-gray-600">5</option>
                    <option value="10" className="text-gray-600">10</option>
                    <option value="50" className="text-gray-600">50</option>
                    <option value="100" className="text-gray-600">100</option>
                </select>
            </div>
            <div class="flex items-start mb-6">
                <div class="flex items-center h-5">
                    <input id="remember" type="checkbox" value=""
                        class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 
                    dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 
                    dark:focus:ring-offset-gray-800" required />
                </div>
                <label for="remember" class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Remember me</label>
            </div>
            <button type="submit"
                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 
            font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 
            dark:focus:ring-blue-800">Submit</button>
        </form>

    );
}
export default SearchForm;