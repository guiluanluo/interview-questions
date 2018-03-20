package Q10_06_Sort_Big_file;

/**
 * Sort big file: image you have a 20 GB file with one string per line. explain how you would sort the file.
 *
 * hint 207: think about merge sort versus quick sort. would one of them work well for this purpose?
 *
 * Solution: when an interviewer gives a size of 20 GB, it should tell you something. in this case, it suggests that
 * they don't want you to bring all the data into memory.
 *
 * so what do we do? we only bring part of the data into memory. we will divide the file into chunks, which are x
 * megabytes each, where x is the amount of memory we have available. each chunk is sorted separately and then saved
 * back to the file system.
 *
 * once all the chunks are sort, we merge the chunks, one by one. at the end, we have a fully sorted file.
 *
 * this algorithm is known as external sort!!
 */
public class Question {

}
